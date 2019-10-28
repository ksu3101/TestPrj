package com.example.mvvm_coroutine.model.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.mvvm_coroutine.model.base.redux.State
import com.example.mvvm_coroutine.model.base.redux.StateStore
import org.koin.androidx.scope.currentScope

/**
 *
 * @author ksu3101
 * @since 2019-10-23
 */
abstract class BaseActivity<S: State> :AppCompatActivity(), LifecycleOwner {

    protected val stateStore: StateStore<S> by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stateStore.stateListener(this) {

        }
    }

}