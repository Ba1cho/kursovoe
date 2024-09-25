package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WaterIntake::class], version = 1)
abstract class WaterIntakeDatabase : RoomDatabase() {

    abstract fun waterIntakeDao(): WaterIntakeDao

    companion object {
        @Volatile private var instance: WaterIntakeDatabase? = null

        fun getInstance(context: Context): WaterIntakeDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    WaterIntakeDatabase::class.java,
                    "WaterTracker"
                ).build().also { instance = it }
            }
        }
    }
}