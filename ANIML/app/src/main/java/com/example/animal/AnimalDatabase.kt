package com.example.animal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityAnimal::class], version = 2, exportSchema = false)
abstract class AnimalDatabase : RoomDatabase() {

    abstract fun AnimalDao(): DaoAnimal

    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null
        fun getDatabase(context: Context): AnimalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {  // protection from concurrent execution on multiple threads
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalDatabase::class.java,
                    "Animals"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
