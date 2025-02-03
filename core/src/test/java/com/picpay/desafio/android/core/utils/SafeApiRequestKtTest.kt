package com.picpay.desafio.android.core.utils

import com.picpay.desafio.android.core.mocks.FakeApiWithRequestInterface
import com.picpay.desafio.android.core.mocks.FakeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class SafeApiRequestKtTest {
    private val mockApi = mockk<FakeApiWithRequestInterface>()
    private val fakeRepository = FakeRepository(mockApi)

    @Test
    fun `when request is successful should return ResultHandler as success`() = runTest {
        coEvery { mockApi.doRequest() } returns Unit

        val result = fakeRepository.doRequest()


        result as ResultHandler.Success
        assertEquals(result.data, Unit)
    }

    @Test
    fun `when request is not successful should return ResultHandler as error`() = runTest {
        val throwable = Throwable("Mocked Throwable")

        coEvery { mockApi.doRequest() } throws throwable

        val result = fakeRepository.doRequest()

        result as ResultHandler.Error
        assertEquals(result.throwable, throwable)
    }
}