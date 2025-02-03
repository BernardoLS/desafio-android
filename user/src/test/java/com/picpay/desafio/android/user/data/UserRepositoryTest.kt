package com.picpay.desafio.android.user.data

import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.user.data.local.UserEntity
import com.picpay.desafio.android.user.data.local.UserLocalDataSource
import com.picpay.desafio.android.user.data.mappers.toEntity
import com.picpay.desafio.android.user.data.mappers.toModel
import com.picpay.desafio.android.user.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.presentation.model.UserModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class UserRepositoryTest {
    private val mockRemoteDataSource: UserRemoteDataSource = mockk()
    private val mockLocalDataSource: UserLocalDataSource = mockk()
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        repository = UserRepository(mockRemoteDataSource, mockLocalDataSource)
    }

    @Test
    fun `should return UserResponse list when fetchRemoteUsers is called`() = runTest {
        val remoteUsers = listOf(UserResponse(id = 1, name = "User", username = "user", img = "img"))

        coEvery { mockRemoteDataSource.fetchUsers() } returns ResultHandler.Success(remoteUsers)

        val result = repository.fetchRemoteUsers()

        result as ResultHandler.Success
        assertEquals(remoteUsers, result.data)
        coVerify(exactly = 1) { mockRemoteDataSource.fetchUsers() }
    }

    @Test
    fun `should return UserEntity list when fetchLocalUsers is called`() = runTest {
        val localEntities = listOf(UserEntity(id = 1, name = "User", username = "user", img = "img"))
        val localModel = localEntities.map { it.toModel() }
        coEvery { mockLocalDataSource.fetchUsers() } returns localEntities

        val result = repository.fetchLocalUsers()

        assertEquals(localModel, result)
        coVerify(exactly = 1) { mockLocalDataSource.fetchUsers() }
    }

    @Test
    fun `should insert UserEntity list into database when insertUsers is called`() = runTest {
        val users = listOf(UserModel(id = 1, name = "User", username = "user", img = "img"))
        val entities = users.map { it.toEntity() }

        coEvery { mockLocalDataSource.insertUsers(entities) } just runs

        repository.insertUsers(users)

        coVerify(exactly = 1) { mockLocalDataSource.insertUsers(entities) }
    }

    @Test
    fun `should return a ResultHandler Error when fetchRemoteUsers fails`() = runTest {
        val error = ResultHandler.Error(RuntimeException("Network error"))

        coEvery { mockRemoteDataSource.fetchUsers() } returns  error

        val result = repository.fetchRemoteUsers()

        assertEquals(error, result)
        coVerify(exactly = 1) { mockRemoteDataSource.fetchUsers() }
    }

    @Test
    fun `should return empty list when fetchLocalUsers with an error`() = runTest {
        coEvery { mockLocalDataSource.fetchUsers() } throws RuntimeException("Database error")

        val result = repository.fetchLocalUsers()

        assertEquals(arrayListOf<UserModel>(), result)

        coVerify(exactly = 1) { mockLocalDataSource.fetchUsers() }
    }
}