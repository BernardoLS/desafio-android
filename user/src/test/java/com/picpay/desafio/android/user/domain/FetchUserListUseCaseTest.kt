package com.picpay.desafio.android.user.domain

import com.picpay.desafio.android.core.logger.AppLogger
import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.user.data.mappers.toModel
import com.picpay.desafio.android.user.data.mappers.toResponse
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.presentation.model.UserModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchUserListUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var sut: FetchUserListUseCase
    private val mockLogger: AppLogger = mockk(relaxed = true)
    private val mockRepository: UserRepositoryInterface = mockk()

    @Before
    fun setUp() {
        sut = FetchUserListUseCase(
            repository = mockRepository,
            logger = mockLogger,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `should emit only local users when remote users are the same`() = runTest(testDispatcher) {
        val localUsers = listOf(UserModel(id = 1, name = "Local User", username = "localuser", img = "img"))
        coEvery { mockRepository.fetchLocalUsers() } returns localUsers
        coEvery { mockRepository.fetchRemoteUsers() } returns ResultHandler.Success(localUsers.map { it.toResponse() })

        val result = sut().toList()

        assertEquals(listOf(localUsers), result)
        coVerify { mockRepository.fetchLocalUsers() }
        coVerify { mockRepository.fetchRemoteUsers() }
        coVerify(exactly = 0) { mockRepository.insertUsers(any()) }
    }


    @Test
    fun `should emit local users and then remote users when different`() = runTest(testDispatcher) {
        val localUsers = listOf(UserModel(id = 1, name = "Local User", username = "localuser", img = "img"))
        val remoteUsers = listOf(UserResponse(id = 1, name = "Remote User", username = "remoteuser", img = "img"))

        coEvery { mockRepository.fetchLocalUsers() } returns localUsers
        coEvery { mockRepository.fetchRemoteUsers() } returns ResultHandler.Success(remoteUsers)
        coEvery { mockRepository.insertUsers(remoteUsers.map { it.toModel() }) } just Runs

        val result = sut().toList()

        assertEquals(listOf(localUsers, remoteUsers.map { it.toModel() }), result)
        coVerify { mockRepository.fetchLocalUsers() }
        coVerify { mockRepository.fetchRemoteUsers() }
        coVerify { mockRepository.insertUsers(remoteUsers.map { it.toModel() }) }
    }

    @Test
    fun `should log error and throw when remote fetch fails`() = runTest(testDispatcher) {
        val localUsers = listOf(UserModel(id = 1, name = "User", username = "user", img = "img"))
        val exception = RuntimeException("Network error")

        coEvery { mockRepository.fetchLocalUsers() } returns localUsers
        coEvery { mockRepository.fetchRemoteUsers() } throws exception

        val result = sut().toList()

        assertEquals(listOf(localUsers), result)
        coVerify { mockLogger.logError("Network error", exception.cause) }
    }
}