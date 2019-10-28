package com.example.mvvm_rx2.model.base.redux

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * @author ksu3101
 * @since 2019-10-23
 */

interface ActionProcessor<S: State> {
    fun run(action: Observable<Action>, store: Store<S>): Observable<Action>
}

class ActionProcessorMiddleWare<S: State>(
        val actionProcessor: ActionProcessor<S>
): MiddleWare<S> {
    override fun create(store: Store<S>, next: Dispatcher): Dispatcher {
        val actionEmitter : PublishSubject<Action> = PublishSubject.create()
        val disposable = actionProcessor.run(actionEmitter, store).subscribe {
            store.dispatch(it)
        }
        return { action: Action ->
            next(action)
            actionEmitter.onNext(action)
        }
    }
}

class CombinedActionProcessors<S: State>(
        val actionProcessors: Iterable<ActionProcessor<S>>
) : ActionProcessor<S> {
    override fun run(action: Observable<Action>, store: Store<S>): Observable<Action> {
        return Observable.fromIterable(actionProcessors).flatMap { it.run(action, store) }
    }
}