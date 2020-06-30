package com.example.dogsjetpack.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dogsjetpack.model.DogModel

@Dao
interface DogDao {
    @Query("SELECT * FROM DogModel")
    suspend fun getAllDogs(): List<DogModel>

    @Query("SELECT * FROM DogModel WHERE id = :id")
    suspend fun getDog(id: Int): DogModel

    @Insert
    suspend fun insertDogs(vararg dogs: DogModel): List<Long>

    @Query("DELETE FROM DogModel")
    suspend fun deleteAllDogs()
}