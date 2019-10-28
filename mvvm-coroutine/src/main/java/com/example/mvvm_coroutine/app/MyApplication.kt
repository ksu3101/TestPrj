package com.example.testprj

import androidx.multidex.MultiDexApplication
import com.example.mvvm_coroutine.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author ksu3101
 * @since 2019-10-18
 */
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }

}