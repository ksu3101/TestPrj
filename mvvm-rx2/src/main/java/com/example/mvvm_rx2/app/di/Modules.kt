package com.example.mvvm_rx2.app.di

import com.example.mvvm_rx2.app.helper.MessageHelperImpl
import com.example.mvvm_rx2.model.base.BaseActivity
import com.example.mvvm_rx2.model.base.helper.MessageHelper
import com.example.mvvm_rx2.model.base.redux.CombinedActionProcessors
import com.example.mvvm_rx2.model.base.redux.State
import com.example.mvvm_rx2.model.domain.AppReducer
import com.example.mvvm_rx2.model.domain.AppState
import com.example.mvvm_rx2.model.domain.AppStore
import com.example.mvvm_rx2.model.domain.login.LoginFragmentVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @author ksu3101
 * @since 2019-10-18
 */
val appModule = module {
    single {
        AppState(mutableMapOf())
        AppStore(AppReducer(), get())
    }
}

val middleWares = module {
    single { CombinedActionProcessors<State>(listOf(

    )) }
}

val helpers = module {
    single <MessageHelper> { MessageHelperImpl(androidContext()) }
}

val viewModels = module {
    viewModel { LoginFragmentVM(get()) }
}