package com.picpay.desafio.android.core.utils

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class ResultHandlerTest {
    @Test
    fun `when ResultHandler is success then data should have the correct value`() {
        val data = "mocked data"
        val result = ResultHandler.Success(data)

        assertEquals(data, result.data)
    }

    @Test
    fun `when ResultHandler is error then a throwable should be thrown with correct value`() {
        val expectedThrowable = Throwable("Mocked Throwable")
        val result: ResultHandler<Nothing> = ResultHandler.Error(expectedThrowable)

        assertEquals(expectedThrowable, (result as ResultHandler.Error).throwable)
        assertEquals("Mocked Throwable", result.throwable.message)
    }
}