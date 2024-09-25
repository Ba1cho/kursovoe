package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TrackerEntity::class], version = 2, exportSchema = false)
abstract class TrackerDatabase : RoomDatabase() {

    abstract fun trackerDao(): TrackerDao

    companion object {
        @Volatile
        private var INSTANCE: TrackerDatabase? = null

        fun getInstance(context: Context): TrackerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrackerDatabase::class.java,
                    "tracker_database"
                )
                    .fallbackToDestructiveMigration() // Временное использование разрушительной миграции
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
