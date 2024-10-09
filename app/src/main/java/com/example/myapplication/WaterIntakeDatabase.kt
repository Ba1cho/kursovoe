package com.example.myapplication

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [WaterIntake::class],
    version = 3,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ],
    exportSchema = true
)

abstract class WaterIntakeDatabase : RoomDatabase() {

    abstract fun waterIntakeDao(): WaterIntakeDao

    companion object {
        @Volatile private var instance: WaterIntakeDatabase? = null

        //р миграция
        private val migration = object: Migration(3,4){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE WaterIntake ADD COLUMN error INTEGER NOT NULL DEFAULT 3")
            }
        }

        fun getInstance(context: Context): WaterIntakeDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    WaterIntakeDatabase::class.java,
                    "WaterTracker"
                ).addMigrations(migration)

                    .build().also { instance = it }
            }
        }
    }
}