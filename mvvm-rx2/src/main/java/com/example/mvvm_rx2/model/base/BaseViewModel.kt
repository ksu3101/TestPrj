package com.example.mvvm_rx2.model.base

import androidx.lifecycle.ViewModel
import com.example.mvvm_rx2.model.base.redux.State
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author ksu3101Ø
 * @since 2019-10-15
 */
abstract class BaseLifecycleOwnViewModel<S : State> : ViewModel(), RxDisposer {
    private lateinit var compositeDisposable: CompositeDisposable

    abstract fun render(state: S): Boolean

    override fun addDisposer(disposable: Disposable) {
        if (!::compositeDisposable.isInitialized) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        if (::compositeDisposable.isInitialized) {
            compositeDisposable.dispose()
        }
    }
}
