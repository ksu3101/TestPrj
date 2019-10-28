package com.example.mvvm_coroutine.model.base.redux

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @author ksu3101
 * @since 2019-10-15
 */

interface State

class Action<S: State>(private val reducer: S.() -> S) {
    operator fun invoke(state: S) = state.reducer()
}

class StateStore<S: State>(
        initialState: S
): CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    private val liveData = MutableLiveData<S>().apply {
        value = initialState
    }

    fun stateListener(owner: LifecycleOwner, renderer: (S) -> Unit) =
            liveData.observe(owner, Observer { renderer(it!!) })

    @MainThread
    private fun updateState(state: S) {
        this.liveData.value = state
    }

    fun dispatchAction(actionProcessor: suspend (S) -> Action<S>) {
        launch {
            val resultAction = actionProcessor(state())
            withContext(Dispatchers.Main) {
                updateState(resultAction(state()))
            }
        }
    }

    fun state() = liveData.value!!

    fun cancel() = job.cancel()

}


