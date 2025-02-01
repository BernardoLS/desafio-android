package com.picpay.desafio.android.user.data.local

import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserLocalDataSourceTest {
    private val userDao: UserDao = mockk()
    private lateinit var localDataSource: UserLocalDataSource

    @Before
    fun setup() {
        localDataSource = UserLocalDataSource(userDao)
    }

    @Test
    fun `should return users from database`() {
        val users = listOf(UserEntity(id = 1, name = "User", username = "user", img = "img"))
        coEvery { userDao.fetchUsers() } returns users

        val result = localDataSource.fetchUsers()

        assertEquals(users, result)
        coVerify { userDao.fetchUsers() }
    }

    @Test
    fun `should insert users into database`() = runTest {
        val users = listOf(UserEntity(id = 1, name = "User", username = "user", img = "img"))
        coEvery { userDao.insertUsers(users) } just Runs

        localDataSource.insertUsers(users)

        coVerify { userDao.insertUsers(users) }
    }
}