package com.example.mvvm_rx2.model.base.helper

import androidx.annotation.StringRes
import com.example.mvvm_rx2.model.base.redux.Action
import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * @author burkd
 * @since 2019-11-01
 */
interface MessageHelper {

    fun showingGeneralToast(@StringRes message: Int)

    fun showingErrorToast(@StringRes messageResId: Int, message: String?)

    fun createOneButtonDialog(
            @StringRes title: Int,
            @StringRes message: Int
    ) : Completable

    fun createTwoButtonDialog(
            @StringRes title: Int,
            @StringRes message: Int
    ) : Maybe<Boolean>

    fun createReTryActionDialog(
            @StringRes title: Int,
            @StringRes message: Int,
            action: Action
    ) : Completable

}