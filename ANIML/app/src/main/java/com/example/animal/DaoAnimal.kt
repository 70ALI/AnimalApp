package com.example.animal

import androidx.room.*

@Dao
interface DaoAnimal {

    @Insert
    suspend fun addAnimal(animal: EntityAnimal)

    @Update
    suspend fun updateAnimal(animal: EntityAnimal)

    @Delete
    suspend fun deleteAnimal(animal: EntityAnimal)

    @Query("SELECT * FROM Animals")
    suspend fun getAllAnimals(): List<EntityAnimal>
}
