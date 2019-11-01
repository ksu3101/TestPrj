package com.example.mvvm_rx2.model.domain

import androidx.annotation.StringRes
import com.example.mvvm_rx2.model.base.redux.Action


/**
 *
 * @author burkd
 * @since 2019-11-01
 */
sealed class MessageAction : Action {
    abstract val messageResId: Int
    abstract val message: String?
}

data class InitializedMessageState(
        // none use this message instances.
        override val messageResId: Int = 0,
        override val message: String? = null
): MessageAction()

data class ShowingGeneralToast(
        override val messageResId: Int = 0,
        override val message: String? = null
) : MessageAction()

data class ShowingErrorToast(
        override val messageResId: Int = 0,
        override val message: String? = null
) : MessageAction()

data class ShowingOneButtonDialog(
        override val messageResId: Int = 0,
        override val message: String? = null
) : MessageAction()

data class ShowingTwoButtonDialog(
        override val messageResId: Int = 0,
        override val message: String? = null
) : MessageAction()

data class ShowingReTryActionDialog(
        override val messageResId: Int = 0,
        val retryAction: Action,
        override val message: String? = null
) : MessageAction()