package com.kldmohammed.yassir.movapp.application

import android.app.Application
import com.kldmohammed.yassir.movapp.common.di.appModule
import com.kldmohammed.yassir.movapp.common.di.movieModule
import org.koin.core.context.startKoin

class MovAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
//            androidLogger()
            modules(
                appModule,
                movieModule,
            )
        }
    }
}