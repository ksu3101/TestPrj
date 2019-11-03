package com.example.mvvm_rx2.model.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_rx2.model.base.helper.MessageHelper
import com.example.mvvm_rx2.model.domain.AppStore
import com.example.mvvm_rx2.model.domain.common.HandledMessageAction
import com.example.mvvm_rx2.model.domain.common.MessageState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

/**
 * @author burkd
 * @since 2019-11-01
 */
abstract class BaseActivity: AppCompatActivity() {

    protected val messageHelper: MessageHelper by inject()
    protected val stateStore: AppStore by inject()

    @LayoutRes
    abstract fun getLayoutId(): Int

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onResume() {
        super.onResume()
        handleAppState()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::compositeDisposable.isInitialized) {
            compositeDisposable.clear()
        }
    }

    private fun handleAppState() {
        if (!::compositeDisposable.isInitialized) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(
                stateStore.getStateListener()
                        .flatMap { Observable.fromIterable(it.states.values) }
                        .ofType(MessageState::class.java)
                        .doOnNext {
                            stateStore.dispatch(HandledMessageAction)
                        }
                        .subscribe {
                            handleMessageState(it)
                        }
        )
    }

    private fun handleMessageState(msgState: MessageState) {
        when (msgState) {

        }
    }

}