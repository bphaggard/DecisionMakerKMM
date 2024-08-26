package com.example.decisionkmm.android

import android.app.Application
import com.example.decisionkmm.android.di.databaseModule
import com.example.decisionkmm.android.di.viewModelsModule
import com.example.decisionkmm.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DecisionApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule + databaseModule

        startKoin {
            androidContext(this@DecisionApp)
            modules(modules)
        }
    }
}