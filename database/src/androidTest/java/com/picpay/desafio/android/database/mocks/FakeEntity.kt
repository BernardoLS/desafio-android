package com.picpay.desafio.android.database.mocks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_entity")
data class TestEntity(
    @PrimaryKey val id: Int,
    val name: String
)