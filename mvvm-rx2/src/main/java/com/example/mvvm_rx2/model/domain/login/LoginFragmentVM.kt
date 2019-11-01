package com.example.mvvm_rx2.model.domain.login

import com.example.mvvm_rx2.model.base.BaseLifecycleOwnViewModel
import com.example.mvvm_rx2.model.base.helper.MessageHelper


/**
 * @author burkd
 * @since 2019-11-01
 */
class LoginFragmentVM(
        val messageHelper: MessageHelper
) : BaseLifecycleOwnViewModel<LoginState>() {

    override fun render(state: LoginState): Boolean {
        return true
    }

}