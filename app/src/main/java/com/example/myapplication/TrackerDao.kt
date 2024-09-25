package com.example.myapplication

import androidx.room.*

@Dao
interface TrackerDao {




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tracker: TrackerEntity)

    @Query("SELECT progress FROM tracker WHERE date = :date")
    fun getProgressByDate(date: String): Int

    @Query("UPDATE tracker SET progress = :progress WHERE date = :date")
    fun updateProgress(date: String, progress: Int)

    @Update
    fun update(tracker: TrackerEntity)

    @Query("SELECT date, progress FROM tracker ORDER BY date DESC")
    fun getAllSummaries(): List<TrackerSummary>

    @Query("DELETE FROM tracker WHERE date = :date")
    fun deleteWaterIntakeByDate(date: String)
}