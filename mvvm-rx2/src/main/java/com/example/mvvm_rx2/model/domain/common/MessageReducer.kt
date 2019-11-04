package com.example.mvvm_rx2.model.domain.common

import com.example.mvvm_rx2.model.base.redux.Action
import com.example.mvvm_rx2.model.base.redux.Reducer

/**
 * @author burkd
 * @since 2019-11-04
 */

class MessageReducer : Reducer<MessageState> {

    override fun reduce(oldState: MessageState, resultAction: Action): MessageState {
        when (resultAction) {
            is HandledMessageAction -> HandledMessageState
            is ShowingGeneralToast -> ShowingGeneralToastState(resultAction.messageResId)
            is ShowingErrorToast -> ShowingErrorToastState(resultAction.messageResId)
            is ShowingOneButtonDialog -> ShowingOneButtonDialogState(
                    resultAction.title,
                    resultAction.messageResId,
                    resultAction.isErrorDialog
            )
            is ShowingReTryActionDialog -> ShowingReTryActionDialogState(
                    resultAction.title,
                    resultAction.messageResId,
                    resultAction.retryAction
            )
        }
        return oldState
    }

}
