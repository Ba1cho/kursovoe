package com.example.myapplication

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class WaterIntake1(goal: Int, progress: Int, date: String) : RealmObject {
    @PrimaryKey
    var id: String = ""
    var goal: Int = 0
    var progress: Int = 0
    var date: String = ""

    constructor(id: String, goal: Int, progress: Int, date: String) : this(8, 0, date) {
        this.id = id
        this.goal = goal
        this.progress = progress
        this.date = date
    }
}
