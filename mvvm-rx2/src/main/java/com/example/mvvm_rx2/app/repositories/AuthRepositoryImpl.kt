package com.example.mvvm_rx2.app.repositories

import com.example.mvvm_rx2.model.domain.login.AuthRepository
import com.example.mvvm_rx2.model.domain.login.dto.User
import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * todo
 *
 * @author burkd
 * @since 2019-11-05
 */
class AuthRepositoryImpl : AuthRepository {
    override fun login(userId: String, passWord: String): Maybe<User> {
        return Maybe.just(User("userId", "nickName"))
    }

    override fun logOut(userId: String): Completable {
        return Completable.complete()
    }

}