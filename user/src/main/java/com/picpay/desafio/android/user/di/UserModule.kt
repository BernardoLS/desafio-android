package com.picpay.desafio.android.user.di

import com.picpay.desafio.android.database.AppDatabase
import com.picpay.desafio.android.user.data.UserRepository
import com.picpay.desafio.android.user.data.local.UserDatabase
import com.picpay.desafio.android.user.data.local.UserLocalDataSource
import com.picpay.desafio.android.user.data.remote.UserRemoteDataSource
import org.koin.dsl.module

val userModule = module {
    single {
        AppDatabase.getInstance<UserDatabase>(
            context = get(),
            databaseName = "user_database"
        )
    }

    single { get<UserDatabase>().userDao() }
    single { UserLocalDataSource(get()) }
    single { UserRemoteDataSource(get()) }
    single { UserRepository(get(), get()) }
}