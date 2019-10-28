package com.example.domain.base

import androidx.lifecycle.ViewModel
import com.example.mvvm_coroutine.model.base.redux.State

/**
 * @author ksu3101Ø
 * @since 2019-10-15
 */
abstract class BaseLifecycleOwnViewModel<S: State> : ViewModel() {

    abstract fun render(state: S): Boolean

}

inline fun <reified S: State> BaseLifecycleOwnViewModel<S>.getStateType(): Class<S> = S::class.java