package com.example.mvvm_rx2.app

import androidx.multidex.MultiDexApplication
import com.example.mvvm_rx2.app.base.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author ksu3101
 * @since 2019-10-18
 */
class RxMvvmApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RxMvvmApplication)
            modules(appModule)
            modules(repositories)
            modules(middleWares)
            modules(viewModels)
            modules(helpers)
        }
    }

}