package com.example.domain.base

import com.example.mvvm_coroutine.model.base.redux.State

/**
 *
 * @author ksu3101
 * @since 2019-10-18
 */
interface NavigationHelper<S: State> {

    fun handle(state: S)

}