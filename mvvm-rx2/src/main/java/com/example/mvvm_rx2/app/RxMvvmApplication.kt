package com.example.mvvm_rx2.app

import androidx.multidex.MultiDexApplication
import com.example.mvvm_rx2.app.di.appModule
import com.example.mvvm_rx2.app.di.middleWares
import com.example.mvvm_rx2.app.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author ksu3101
 * @since 2019-10-18
 */
class RxMvvmApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RxMvvmApplication)
            modules(appModule)
            modules(middleWares)
            modules(viewModels)
        }
    }

}