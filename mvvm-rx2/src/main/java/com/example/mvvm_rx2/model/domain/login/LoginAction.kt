package com.example.mvvm_rx2.model.domain.login

import com.example.mvvm_rx2.model.base.redux.Action

/**
 *
 * @author burkd
 * @since 2019-11-01
 */

sealed class LoginAction: Action

data class UserLoginAction(
        val userId: String,
        val passWord: String
): LoginAction()