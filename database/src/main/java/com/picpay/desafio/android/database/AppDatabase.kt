package com.picpay.desafio.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        inline fun <reified T : RoomDatabase> getInstance(
            context: Context,
            databaseName: String,
        ): T {
        return Room.databaseBuilder(
            context.applicationContext,
                T::class.java,
                databaseName
            ).build()
        }
    }
}