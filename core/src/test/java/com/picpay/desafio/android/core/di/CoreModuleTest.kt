package com.picpay.desafio.android.core.di

import com.picpay.desafio.android.core.logger.AppLogger
import com.picpay.desafio.android.core.logger.PicPayLogger
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.assertNotNull

class CoreModuleTest: KoinTest {
    private lateinit var mockModule: Module

    @Before
    fun setUp() {
        mockModule = module {
            single<AppLogger> { mockk<PicPayLogger>() }
        }

        startKoin {
            modules(mockModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `PicPayLogger should be injected correctly`() {
        val logger: AppLogger = get()
        assert(logger is PicPayLogger)
        assertNotNull(logger)
    }
}