package com.picpay.desafio.android.user.data.remote

import com.picpay.desafio.android.core.utils.ResultHandler
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRemoteDataSourceTest {
    private lateinit var sut: UserRemoteDataSource
    private val mockApi = mockk<UserApiService>()

    @Before
    fun setUp() {
        sut = UserRemoteDataSource(mockApi)
    }

    @Test
    fun `when fetch users with success should return UserResponse list`() = runTest {
        val response = listOf(UserResponse(id = 1, name = "User", username = "user", img = "img"))

        coEvery { mockApi.fetchUsers() } returns  response

        val result = sut.fetchUsers()

        result as ResultHandler.Success
        assertEquals(result.data, response)
        coVerify(exactly = 1) { mockApi.fetchUsers() }
    }

    @Test
    fun `when fetch users with error should throw an exception`() = runTest {
        val throwable = RuntimeException("Network error")

        coEvery { mockApi.fetchUsers() } throws throwable

        val result = sut.fetchUsers()

        result as ResultHandler.Error

        assertEquals(result.throwable, throwable)
        coVerify(exactly = 1) { mockApi.fetchUsers() }
    }

}