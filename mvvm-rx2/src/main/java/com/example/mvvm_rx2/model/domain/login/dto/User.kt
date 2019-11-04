package com.example.mvvm_rx2.model.domain.login.dto

import com.example.mvvm_rx2.extension.isNotNullOrEmpty

/**
 * @author burkd
 * @since 2019-11-05
 */
data class User(
        val id: String,
        val nickName: String

        // todo : user infos..
)

fun User?.isAvailableUser(): Boolean = this?.id?.isNotNullOrEmpty() ?: false