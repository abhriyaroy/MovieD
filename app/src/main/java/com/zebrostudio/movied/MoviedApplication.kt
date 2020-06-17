package com.zebrostudio.movied

import android.app.Application
import com.zebrostudio.movied.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviedApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val moduleList = listOf(appModule)
        startKoin {
            androidContext(this@MoviedApplication)
            modules(moduleList)
        }
    }
}