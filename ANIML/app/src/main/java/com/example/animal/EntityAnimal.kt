package com.example.animal

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Animals")
data class EntityAnimal(
    @PrimaryKey(autoGenerate = true) val pk: Int,
    val type: String,
    val condition:String,
    val summary : String,
    val price : String
)

