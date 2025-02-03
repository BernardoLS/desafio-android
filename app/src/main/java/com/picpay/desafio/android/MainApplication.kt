package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.user.di.getUserApiModule
import com.picpay.desafio.android.user.di.userModule
import com.picpay.desafio.android.core.di.coreModule
import com.picpay.desafio.android.core.logger.AppLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(userModule + getUserApiModule(BuildConfig.BASE_URL) + coreModule)
        }

        val logger: AppLogger = getKoin().get()
        logger.init()
    }
}