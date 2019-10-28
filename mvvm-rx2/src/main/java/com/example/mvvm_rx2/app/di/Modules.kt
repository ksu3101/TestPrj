package com.example.mvvm_rx2.app.di

import com.example.mvvm_rx2.model.base.redux.CombinedActionProcessors
import com.example.mvvm_rx2.model.base.redux.State
import org.koin.dsl.module

/**
 * @author ksu3101
 * @since 2019-10-18
 */
val appModule = module {

}

val middleWares = module {
    single { CombinedActionProcessors<State>(listOf(

    )) }
}