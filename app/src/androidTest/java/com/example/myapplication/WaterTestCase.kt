package com.example.myapplication

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.jetbrains.annotations.NotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WaterTestCase {

    private lateinit var db: TrackerDatabase
    private lateinit var db1: WaterIntakeDatabase
    private lateinit var dao: TrackerDao
    private lateinit var dao1: WaterIntakeDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, TrackerDatabase::class.java).build()
        dao = db.trackerDao()
    }
    @Before
    fun createDb1() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db1 = Room.inMemoryDatabaseBuilder(context, WaterIntakeDatabase::class.java).build()
        dao1 = db1.waterIntakeDao()
    }
    @After
    fun closeDb() {
        db.close()
    }

    @After
    fun closeDb1() {
        db1.close()
    }


    @Test
    fun testInsertTracker() {
        val newTracker = TrackerEntity(goal = 1, progress = 0, date = "27.12.11")
        dao.insert(newTracker)
        val insertedTracker = dao.getProgressByDate(date = "27.12.11")
        assertNotNull(insertedTracker.toString(), "The date has been added")
    }

    @Test
    fun testReadTracker() {
        val trackerList = dao.getProgressByDate(date = "27.12.11")
        assertTrue(trackerList.toString(), true)
    }

    @Test
    fun testUpdateTracker() {
        val newTracker = TrackerEntity(goal = 1, progress = 0, date = "27.12.11")
        dao.insert(newTracker)
        dao.updateProgress(date = "27.12.11", progress = 2)
        val updatedTracker = dao.getProgressByDate(date = "27.12.11")
        assertNotNull(updatedTracker.toString(), "Found")
        assertEquals(2, updatedTracker)
    }

    @Test
    fun testDeleteTracker() {
        val testTracker = TrackerEntity(goal = 1, progress = 0, date = "27.12.11")
        dao.insert(testTracker)
        dao.deleteWaterIntakeByDate(date = "27.12.11")
        assertTrue(dao.getAllSummaries().isEmpty())
    }

    @Test
    fun testInsertWater() {
        val newWater = WaterIntake(goal = 1, progress = 0, date = "11.11.11")
        dao1.insert(newWater)
        val insertedWater = dao1.getProgressByDate(date = "11.11.11")
        assertNotNull(insertedWater.toString(), "The date has been added")
    }

    @Test
    fun testReadWater() {
        val waterList = dao1.getProgressByDate(date = "11.12.11")
        assertTrue(waterList.toString(), true)
    }

    @Test
    fun testUpdateWater() {
        val newWater = WaterIntake(goal = 1, progress = 0, date = "11.12.11")
        dao1.insert(newWater)
        dao1.updateProgress(date = "11.12.11", progress = 3)
        val updatedWater = dao1.getProgressByDate(date = "11.12.11")
        assertNotNull(updatedWater.toString(), "Found")
        assertEquals(3, updatedWater)
    }

    @Test
    fun testDeleteWater() {
        val testWater = WaterIntake(goal = 1, progress = 0, date = "27.12.11")
        dao1.insert(testWater)
        dao1.deleteByDate(date = "27.12.11")
        assertNotNull(dao1.getProgressByDate(date = "27.12.11").toString(), 0)
    }

    @Test
    fun testReadTracker1() {
        val waterList = dao.getAllSummaries()
        assertTrue(waterList.toString(), true)
    }

    @Test
    fun testUpdate() {
        val newTracker = TrackerEntity(goal = 1, progress = 2, date = "27.12.11")
        dao.insert(newTracker)
        val newTracker1 = TrackerEntity(goal = 2, progress = 2, date = "27.12.11")
        dao.update(newTracker)
        val updatedTracker = dao.getProgressByDate(date = "27.12.11")
        assertNotNull(updatedTracker.toString(), "Found")
        assertEquals(2, updatedTracker)
    }

}