package com.picpay.desafio.android.core.di

import com.picpay.desafio.android.core.logger.AppLogger
import com.picpay.desafio.android.core.logger.PicPayLogger
import org.koin.dsl.module

val coreModule = module {
    single<AppLogger> { PicPayLogger() }
}