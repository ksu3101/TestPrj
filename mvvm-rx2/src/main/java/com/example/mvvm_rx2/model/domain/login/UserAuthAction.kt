package com.example.mvvm_rx2.model.domain.login

import com.example.mvvm_rx2.model.base.redux.Action
import com.example.mvvm_rx2.model.domain.login.dto.User

/**
 *
 * @author burkd
 * @since 2019-11-01
 */

sealed class UserAuthAction : Action

data class UserLoginAction(
        val userId: String,
        val passWord: String
) : UserAuthAction()

data class UserLoginSuccess(
        val userInfo: User
) : UserAuthAction()

data class UserLogoutAction(
        val userId: String
) : UserAuthAction()

object UserLogoutSuccess : UserAuthAction()
