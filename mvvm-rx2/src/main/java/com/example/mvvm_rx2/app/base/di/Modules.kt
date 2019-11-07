package com.example.mvvm_rx2.app.base.di

import com.example.mvvm_rx2.app.base.helper.MessageHelperImpl
import com.example.mvvm_rx2.app.base.helper.ResourceHelperImpl
import com.example.mvvm_rx2.app.repositories.AuthRepositoryImpl
import com.example.mvvm_rx2.model.base.BaseLifecycleOwnViewModel
import com.example.mvvm_rx2.model.base.helper.MessageHelper
import com.example.mvvm_rx2.model.base.helper.ResourceHelper
import com.example.mvvm_rx2.model.base.redux.ActionProcessorMiddleWare
import com.example.mvvm_rx2.model.base.redux.CombinedActionProcessors
import com.example.mvvm_rx2.model.domain.AppReducer
import com.example.mvvm_rx2.model.domain.AppState
import com.example.mvvm_rx2.model.domain.AppStore
import com.example.mvvm_rx2.model.domain.common.MessageReducer
import com.example.mvvm_rx2.model.domain.login.AuthActionProcessor
import com.example.mvvm_rx2.model.domain.login.AuthRepository
import com.example.mvvm_rx2.model.domain.login.LoginFragmentVM
import com.example.mvvm_rx2.model.domain.login.UserAuthState
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author ksu3101
 * @since 2019-10-18
 */
val appModule = module {
    single {
        MessageReducer()
    }
    single {
        AppState(mutableMapOf())
    }
    single {
        AppStore(AppReducer(), get())
    }
}

val repositories = module {
    single<AuthRepository> { AuthRepositoryImpl() }
}

val middleWares = module {
    single {
        ActionProcessorMiddleWare(CombinedActionProcessors(listOf(
                AuthActionProcessor(get(), get(), get())
        )))
    }
}

val helpers = module {
    single<MessageHelper> { MessageHelperImpl(androidContext()) }
    single<ResourceHelper> { ResourceHelperImpl(androidApplication()) }
}

val viewModels = module {
    viewModel<BaseLifecycleOwnViewModel<UserAuthState>> { LoginFragmentVM(get(), get()) }
}