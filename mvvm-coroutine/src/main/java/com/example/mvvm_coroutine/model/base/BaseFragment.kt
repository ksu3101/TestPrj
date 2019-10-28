package com.example.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvvm_coroutine.model.base.redux.State
import com.example.mvvm_coroutine.model.base.redux.StateStore
import org.koin.android.ext.android.inject

/**
 * @author ksu3101
 * @since 2019-10-18
 */
open class BaseFragment<S : State> : Fragment() {

    protected val stateStore: StateStore<S> by inject()
    protected val viewModel: BaseLifecycleOwnViewModel<S> by inject()
    protected val navigationHelper: NavigationHelper<S> by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        stateStore.stateListener(viewLifecycleOwner) {
            if (!viewModel.render(it)) {
                navigationHelper.handle(it)
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        stateStore.cancel()
    }
}

inline fun <reified S: State> BaseFragment<S>.getStateType(): Class<S> = S::class.java
