package com.picpay.desafio.android.user.di

import com.picpay.desafio.android.database.AppDatabase
import com.picpay.desafio.android.network.retrofit.ClientInterceptor
import com.picpay.desafio.android.network.retrofit.RetrofitClient
import com.picpay.desafio.android.user.data.UserRepository
import com.picpay.desafio.android.user.data.local.UserDatabase
import com.picpay.desafio.android.user.data.local.UserLocalDataSource
import com.picpay.desafio.android.user.data.remote.UserApiService
import com.picpay.desafio.android.user.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.user.domain.FetchUserListUseCase
import com.picpay.desafio.android.user.domain.UserRepositoryInterface
import com.picpay.desafio.android.user.presentation.view.UserViewModel
import org.koin.core.module.dsl.viewModel
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
    single<UserRepositoryInterface> { UserRepository(get(), get()) }
    factory { FetchUserListUseCase(get()) }
    viewModel { UserViewModel(get()) }
}

fun getUserApiModule(baseUrl: String) = module {
    factory {
        RetrofitClient.instance(
            baseUrl,
            UserApiService::class.java,
            ClientInterceptor.buildClient()
        )
    }
}