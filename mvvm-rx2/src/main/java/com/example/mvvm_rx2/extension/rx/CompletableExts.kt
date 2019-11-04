package com.example.mvvm_rx2.extension.rx

import com.example.mvvm_rx2.model.base.redux.Action
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author burkd
 * @since 2019-11-05
 */

inline fun Completable.andActionObservable(next: () -> Action): Observable<Action> {
    return this.andThen(Observable.just(next()))
}