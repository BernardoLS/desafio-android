package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.user.di.getUserApiModule
import com.picpay.desafio.android.user.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(userModule + getUserApiModule(BuildConfig.BASE_URL))
        }
    }
}