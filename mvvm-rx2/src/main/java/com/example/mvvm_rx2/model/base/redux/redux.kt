package com.example.mvvm_rx2.model.base.redux

import io.reactivex.Observable

/**
 * @author ksu3101
 * @since 2019-10-15
 */

interface State

interface Action

interface Reducer<S: State> {
    fun reduce(oldState: S, resultAction: Action): S
}

inline fun <reified S:State> Reducer<S>.getStateTypeOfReducer() : Class<S> = S::class.java

interface Store<S: State> {
    fun dispatch(action: Action)
    fun getStateListener(): Observable<S>
    fun getCurrentState(): S
}

typealias Dispatcher = (Action) -> Unit

interface MiddleWare<S: State> {
    fun create(store: Store<S>, next: Dispatcher): Dispatcher
}
