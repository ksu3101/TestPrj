package com.example.mvvm_rx2.model.domain

import com.example.mvvm_rx2.model.base.redux.Action
import com.example.mvvm_rx2.model.base.redux.Dispatcher
import com.example.mvvm_rx2.model.base.redux.MiddleWare
import com.example.mvvm_rx2.model.base.redux.Reducer
import com.example.mvvm_rx2.model.base.redux.State
import com.example.mvvm_rx2.model.base.redux.Store
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.core.KoinComponent

/**
 * @author ksu3101
 * @since 2019-10-22
 */

class AppStore<S : State>(
        val reducer: Reducer<S>,
        initializedState: S
) : Store<S>, KoinComponent {
    private val stateEmitter: BehaviorSubject<S> = BehaviorSubject.create()
    private var state: S = initializedState
    private val middleWares: List<MiddleWare<S>> = getKoin().getAll()
    private var dispatcher: Dispatcher = { action: Action ->
        state = reducer.reduce(getCurrentState(), action)
        stateEmitter.onNext(state)
    }

    init {
        dispatcher = middleWares.foldRight(dispatcher) { middleWare, next ->
            middleWare.create(this, next)
        }
    }

    override fun getStateListener(): Observable<S> =
            stateEmitter.hide().observeOn(AndroidSchedulers.mainThread())

    override fun getCurrentState(): S = state

    override fun dispatch(action: Action) {
        dispatcher(action)
    }
}

data class AppState(
        val reducers: List<>
) : State

class AppReducer : Reducer<AppState>, KoinComponent {
    override fun reduce(oldState: AppState, resultAction: Action): AppState {
        return AppState(

        )
    }

    inline fun <reified S : State, reified R : Reducer<S>> getReducers(): List<R> =
            getKoin().getAll()
}

