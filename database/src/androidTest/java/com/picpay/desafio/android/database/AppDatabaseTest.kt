package com.picpay.desafio.android.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.filters.SmallTest
import com.picpay.desafio.android.database.mocks.TestDao
import com.picpay.desafio.android.database.mocks.TestDatabase
import com.picpay.desafio.android.database.mocks.TestEntity
import org.junit.Rule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: TestDatabase
    private lateinit var testDao: TestDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        testDao = database.testDao()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun insertAndRetrieveEntity() = runBlocking {
        val entity = TestEntity(id = 1, name = "Test Entity")
        testDao.insert(entity)
        val retrievedEntity = testDao.getById(1)
        assertEquals(entity.id, retrievedEntity?.id)
        assertEquals(entity.name, retrievedEntity?.name)
    }

    @Test
    fun testGetInstance() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val dbName = "test_db"
        val db = AppDatabase.getInstance<TestDatabase>(context, dbName)
        db.close()
    }
}