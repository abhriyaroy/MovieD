package com.zebrostudio.movied

import android.app.Application
import com.zebrostudio.movied.di.appModule
import com.zebrostudio.movied.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviedApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(appModule, mainModule)
        startKoin {
            // Android context
            androidContext(this@MoviedApplication)
            // modules
            modules(moduleList)
        }
    }
}