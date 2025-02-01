package com.picpay.desafio.android.core.utils

import com.picpay.desafio.android.core.mocks.FakeApiWithRequestInterface
import com.picpay.desafio.android.core.mocks.FakeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SafeApiRequestKtTest {
    private val mockApi = mockk<FakeApiWithRequestInterface>()
    private val fakeRepository = FakeRepository(mockApi)

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when request is successful should return ResultHandler as success`() = testScope.runTest {
        coEvery { mockApi.doRequest() } returns Unit

        val result = fakeRepository.doRequest()

        advanceUntilIdle()

        result as ResultHandler.Success
        assertEquals(result.data, Unit)
    }

    @Test
    fun `when request is not successful should return ResultHandler as error`() = testScope.runTest {
        val throwable = Throwable("Mocked Throwable")

        coEvery { mockApi.doRequest() } throws throwable

        advanceUntilIdle()
        val result = fakeRepository.doRequest()

        result as ResultHandler.Error
        assertEquals(result.throwable, throwable)
    }
}