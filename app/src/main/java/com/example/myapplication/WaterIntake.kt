package com.example.myapplication
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracker")
data class WaterIntake(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val goal: Int,
    val progress: Int = 0,
    val date: String,
//    val voda: Int?,   // автомигр
//    val number: Int,  //тоже
//    val error: Int    //тоже
)