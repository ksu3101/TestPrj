package com.example.mvvm_rx2.app.exts

import com.example.mvvm_rx2.model.base.redux.State
import io.reactivex.Observable

/**
 * @author burkdog
 * @since 2019-10-29
 */

inline fun <reified S: State> Observable<S>.isStateType(): Observable<S> = ofType<S>(S::class.java)
