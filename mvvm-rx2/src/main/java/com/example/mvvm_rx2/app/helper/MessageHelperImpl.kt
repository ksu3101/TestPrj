package com.example.mvvm_rx2.app.helper

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.mvvm_rx2.R
import com.example.mvvm_rx2.model.base.helper.MessageHelper
import com.example.mvvm_rx2.model.base.redux.Action
import com.example.mvvm_rx2.model.domain.AppStore
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.disposables.Disposables
import org.koin.core.KoinComponent

/**
 * @author burkd
 * @since 2019-11-01
 */
class MessageHelperImpl(
        val context: Context
) : MessageHelper, KoinComponent {

    override fun showingGeneralToast(message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showingErrorToast(message: Int) {
        // todo : need inflating background color
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun createOneButtonDialog(title: Int, message: Int): Completable =
            createDialogSource(
                    title,
                    message
            ).flatMapCompletable { Completable.complete() }

    override fun createTwoButtonDialog(title: Int, message: Int): Maybe<Boolean> =
            createDialogSource(
                    title,
                    message
            )

    override fun createReTryActionDialog(title: Int, message: Int, action: Action): Completable =
            createDialogSource(
                    title,
                    message,
                    positiveReceiver = {
                        getKoin().get<AppStore>().dispatch(action)
                    }
            ).flatMapCompletable { Completable.complete() }

    private fun createDialogSource(
            @StringRes title: Int,
            @StringRes message: Int,
            @StringRes positiveBtnResId: Int = R.string.c_yes,
            positiveReceiver: (() -> Unit)? = null,
            @StringRes negativeBtnResId: Int = R.string.c_no,
            negativeReceiver: (() -> Unit)? = null,
            isCancelable: Boolean = true,
            isTwoBtn: Boolean = false): Maybe<Boolean> =
            Maybe.create { emitter ->
                val builder = AlertDialog.Builder(context)
                builder.setTitle(context.getString(title))
                builder.setMessage(context.getString(message))
                builder.setPositiveButton(context.getString(positiveBtnResId)) { _, _ ->
                    positiveReceiver.let { it }
                    emitter.onSuccess(true)
                    emitter.onComplete()
                }
                if (isTwoBtn) {
                    builder.setNegativeButton(context.getString(negativeBtnResId)) { _, _ ->
                        negativeReceiver.let { it }
                        emitter.onSuccess(false)
                        emitter.onComplete()
                    }
                }
                if (isCancelable) {
                    builder.setOnCancelListener {
                        if (isTwoBtn) emitter.onSuccess(false)
                        emitter.onComplete()
                    }
                }
                val dialog = builder.create()
                emitter.setDisposable(Disposables.fromRunnable {
                    dialog.dismiss()
                })
                dialog.show()
            }

}