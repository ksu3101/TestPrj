package com.example.mvvm_rx2.model.domain

import com.example.mvvm_rx2.model.base.redux.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.core.KoinComponent

/**
 * @author ksu3101
 * @since 2019-10-22
 */

class AppStore(
        val reducer: Reducer<AppState>,
        initializedState: AppState
) : Store<AppState>, KoinComponent {
    private val stateEmitter: BehaviorSubject<AppState> = BehaviorSubject.create()
    private var state: AppState = initializedState
    private val middleWares: List<MiddleWare<AppState>> = getKoin().getAll()
    private var dispatcher: Dispatcher = { action: Action ->
        state = reducer.reduce(getCurrentState(), action)
        stateEmitter.onNext(state)
    }

    init {
        dispatcher = middleWares.foldRight(dispatcher) { middleWare, next ->
            middleWare.create(this, next)
        }
    }

    override fun getStateListener(): Observable<AppState> =
            stateEmitter.hide().observeOn(AndroidSchedulers.mainThread())

    override fun getCurrentState(): AppState = state

    override fun dispatch(action: Action) {
        dispatcher(action)
    }
}

data class AppState(
        val states: Map<String, State>
) : State {
    inline fun <reified S:State> getCurrentState(stateType: Class<S>) : S {
        val currentState = states.get(stateType.simpleName)
                ?: throw NullPointerException("$stateType has not founded error.")
        return currentState as S
    }
}

class AppReducer : Reducer<AppState>, KoinComponent {
    override fun reduce(oldState: AppState, resultAction: Action): AppState {
        return reduces<State, Reducer<State>>(oldState, resultAction)
    }

    private inline fun <reified S: State, reified R: Reducer<S>> reduces(oldState: AppState, resultAction: Action): AppState {
        val states = mutableMapOf<String, S>()
        getReducers<S, R>().map {
            val stateType = it.getStateTypeOfReducer()
            states.put(stateType.simpleName, it.reduce(oldState.getCurrentState(stateType), resultAction))
        }
        return AppState(states)
    }

    private inline fun <reified S : State, reified R : Reducer<S>> getReducers(): List<R> =
            getKoin().getAll()
}

