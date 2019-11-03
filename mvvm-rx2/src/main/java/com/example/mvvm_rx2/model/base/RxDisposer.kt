package com.example.mvvm_rx2.model.base

import io.reactivex.disposables.Disposable

/**
 * @author burkd
 * @since 2019-11-04
 */
interface RxDisposer {

    fun addDisposer(disposable: Disposable)

    fun dispose()
}