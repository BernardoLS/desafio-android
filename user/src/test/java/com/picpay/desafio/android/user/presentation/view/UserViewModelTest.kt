package com.picpay.desafio.android.user.presentation.view

import com.picpay.desafio.android.user.domain.FetchUserListUseCase
import com.picpay.desafio.android.user.presentation.intents.UserListIntents
import com.picpay.desafio.android.user.presentation.model.UserModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserViewModelTest {
    private val mockFetchUserListUseCase: FetchUserListUseCase = mockk()
    private lateinit var sut: UserViewModel

    @Before
    fun setUp() {
        sut = UserViewModel(mockFetchUserListUseCase)
    }

    @Test
    fun `should start with Empty state`() = runTest {
        assertEquals(UserState.Empty, sut.usersState.value)
    }

    @Test
    fun `when intent is loadPage and result is success, then state should be Success`() = runTest {
        val result = listOf(UserModel(id = 1, name = "User", username = "user", img = "img"))
        val flow = flowOf(result)
        coEvery { mockFetchUserListUseCase() } returns flow

        sut.sendIntent(UserListIntents.LoadUsers)

        val state = sut.usersState.first { it is UserState.Success }

        assertTrue(state is UserState.Success)
        assertEquals(result, (state as UserState.Success).users)
    }

    @Test
    fun `when intent is loadPage and result is error, then state should be Error`() = runTest {
        val errorMessage = "Network error"
        coEvery { mockFetchUserListUseCase() } returns flow { throw RuntimeException(errorMessage) }

        sut.sendIntent(UserListIntents.LoadUsers)

        val state = sut.usersState.first { it is UserState.Error }

        assertTrue(state is UserState.Error)
        assertEquals(errorMessage, (state as UserState.Error).message)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should call fetchUsers when LoadUsers intent is sent`() = runTest {
        val result = listOf(UserModel(id = 1, name = "User", username = "user", img = "img"))
        val flow = flowOf(result)

        coEvery { mockFetchUserListUseCase() } returns flow

        sut.sendIntent(UserListIntents.LoadUsers)
        advanceUntilIdle()
        coVerify(exactly = 1) { mockFetchUserListUseCase() }
    }

}