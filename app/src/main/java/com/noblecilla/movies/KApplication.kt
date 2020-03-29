package com.noblecilla.movies

import android.app.Application
import com.noblecilla.movies.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KApplication)
            modules(appModule)
        }
    }
}
