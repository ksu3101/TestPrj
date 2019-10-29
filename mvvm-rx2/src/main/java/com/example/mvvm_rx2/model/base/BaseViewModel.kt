package com.example.mvvm_rx2.model.base

import androidx.lifecycle.ViewModel
import com.example.mvvm_rx2.model.base.redux.State

/**
 * @author ksu3101Ø
 * @since 2019-10-15
 */
abstract class BaseLifecycleOwnViewModel<S: State> : ViewModel() {

    abstract fun render(state: S): Boolean
}
