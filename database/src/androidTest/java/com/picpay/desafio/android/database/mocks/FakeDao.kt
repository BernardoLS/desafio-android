package com.picpay.desafio.android.database.mocks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TestDao {
    @Insert
    suspend fun insert(entity: TestEntity)

    @Query("SELECT * FROM test_entity WHERE id = :id")
    suspend fun getById(id: Int): TestEntity?
}