package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.user.di.getUserApiModule
import com.picpay.desafio.android.user.di.userModule
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(userModule + getUserApiModule(BuildConfig.BASE_URL))
        }
    }
}