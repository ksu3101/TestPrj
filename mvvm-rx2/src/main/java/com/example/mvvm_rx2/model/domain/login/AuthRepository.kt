package com.example.mvvm_rx2.model.domain.login

import com.example.mvvm_rx2.model.domain.login.dto.User
import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * @author burkd
 * @since 2019-11-05
 */
interface AuthRepository {

    fun login(
            userId: String,
            passWord: String
    ): Maybe<User>

    fun logOut(
            userId: String
    ): Completable

}