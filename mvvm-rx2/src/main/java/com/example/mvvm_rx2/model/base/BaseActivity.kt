package com.example.mvvm_rx2.model.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_rx2.model.base.helper.MessageHelper
import com.example.mvvm_rx2.model.domain.AppStore
import com.example.mvvm_rx2.model.domain.common.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

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
        checkCompositeDisposableInstanceAndCreator()
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
            is ShowingGeneralToastState -> {
                messageHelper.showingGeneralToast(msgState.messageResId)
            }
            is ShowingErrorToastState -> {
                messageHelper.showingErrorToast(msgState.messageResId, msgState.message)
            }
            is ShowingOneButtonDialogState -> {
                checkCompositeDisposableInstanceAndCreator()
                compositeDisposable.add( // todo : add error dialog flag and resources
                        messageHelper.createOneButtonDialog(
                                msgState.title,
                                msgState.messageResId)
                                .subscribe()
                )
            }
            is ShowingReTryActionDialogState -> {
                checkCompositeDisposableInstanceAndCreator()
                compositeDisposable.add(
                        messageHelper.createReTryActionDialog(
                                msgState.title,
                                msgState.messageResId,
                                msgState.retryAction
                        ).subscribe()
                )
            }
        }
    }

    private fun checkCompositeDisposableInstanceAndCreator() {
        if (!::compositeDisposable.isInitialized) {
            compositeDisposable = CompositeDisposable()
        }
    }

}