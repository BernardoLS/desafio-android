package com.picpay.desafio.android.user.di

import com.picpay.desafio.android.user.data.UserRepository
import com.picpay.desafio.android.user.data.local.UserDao
import com.picpay.desafio.android.user.data.local.UserDatabase
import com.picpay.desafio.android.user.data.local.UserLocalDataSource
import com.picpay.desafio.android.user.data.remote.UserApiService
import com.picpay.desafio.android.user.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.user.domain.FetchUserListUseCase
import com.picpay.desafio.android.user.presentation.view.UserViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

class UserModuleTest : KoinTest {
    private lateinit var testModule: Module
    private lateinit var apiModule: Module
    @Before
    fun setup() {
        testModule = module {
            factory { mockk<UserDatabase>(relaxed = true) }
            factory { mockk<UserDao>(relaxed = true) }
            factory { mockk<UserLocalDataSource>(relaxed = true) }
            factory { mockk<UserRemoteDataSource>(relaxed = true) }
            factory { mockk<UserRepository>(relaxed = true) }
            factory { mockk<FetchUserListUseCase>(relaxed = true) }
            factory { mockk<UserViewModel>(relaxed = true) }
        }

        apiModule = getUserApiModule("https://api.fake.com")

        startKoin {
            modules(testModule + apiModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `UserDatabase should be inject correctly`() {
        val userDatabase: UserDatabase = get()
        assertNotNull(userDatabase)
    }

    @Test
    fun `UserDao should be inject correctly`() {
        val userDao: UserDao = get()
        assertNotNull(userDao)
    }

    @Test
    fun `userLocalDataSource should be inject correctly`() {
        val localDataSource: UserLocalDataSource = get()
        assertNotNull(localDataSource)
    }

    @Test
    fun `userRemoteDataSource should be inject correctly`() {
        val remoteDataSource: UserRemoteDataSource = get()
        assertNotNull(remoteDataSource)
    }

    @Test
    fun `UserRepository should be inject correctly`() {
        val repository: UserRepository = get()
        assertNotNull(repository)
    }

    @Test
    fun `FetchUsersUseCase should be inject correctly`() {
        val useCase: FetchUserListUseCase = get()
        assertNotNull(useCase)
    }

    @Test
    fun `UserListViewmodel should be inject correctly`() {
        val viewModel: UserViewModel = get()
        assertNotNull(viewModel)
    }

    @Test
    fun `userApiService should be inject correctly`() {
        val apiService: UserApiService = get()
        assertNotNull(apiService)
    }
}
