package com.example.myapplication

import androidx.room.*

@Dao
interface WaterIntakeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(waterIntake: WaterIntake)

    @Query("SELECT progress FROM tracker WHERE date = :date")
    fun getProgressByDate(date: String): Int

    @Query("UPDATE tracker SET progress = :progress WHERE date = :date")
    fun updateProgress(date: String, progress: Int)

    @Query("DELETE FROM tracker WHERE date = :date")
    fun deleteByDate(date: String)
}