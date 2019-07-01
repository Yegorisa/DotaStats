package com.egoregorov.dotastats

import android.app.Application
import com.egoregorov.dotastats.dependencyinjection.networkModules
import com.egoregorov.dotastats.dependencyinjection.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DotaStatsApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DotaStatsApplication)
            modules(listOf(viewModelsModule, networkModules))
        }
    }
}