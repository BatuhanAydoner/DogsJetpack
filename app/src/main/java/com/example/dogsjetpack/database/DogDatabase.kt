package com.example.dogsjetpack.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogsjetpack.model.DogModel

@Database(entities = arrayOf(DogModel::class), version = 1)
abstract class DogDatabase: RoomDatabase() {
    abstract fun dao(): DogDao

    companion object {
        @Volatile
        private var instance: DogDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(context, DogDatabase::class.java, "dogdatabase").build()
    }
}