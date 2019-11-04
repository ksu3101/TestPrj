package com.example.mvvm_rx2.model.domain.common

import androidx.annotation.StringRes
import com.example.mvvm_rx2.model.base.redux.Action


/**
 *
 * @author burkd
 * @since 2019-11-01
 */
sealed class MessageAction : Action

object HandledMessageAction : MessageAction()

data class ShowingGeneralToast(
        @StringRes val messageResId: Int
) : MessageAction()

data class ShowingErrorToast(
        @StringRes val messageResId: Int = 0,
        val message: String? = null
) : MessageAction()

data class ShowingOneButtonDialog(
        @StringRes val title: Int,
        @StringRes val messageResId: Int,
        val isErrorDialog: Boolean = false
) : MessageAction()

data class ShowingReTryActionDialog(
        @StringRes val title: Int,
        @StringRes val messageResId: Int,
        val retryAction: Action
) : MessageAction()