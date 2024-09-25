package com.example.myapplication

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import javax.inject.Inject

interface WaterIntakeDao1 {
    suspend fun createWaterIntake(waterIntake: WaterIntake1)
    suspend fun getWaterIntakeByDate(date: String): WaterIntake1?
    suspend fun updateWaterIntake(waterIntake: WaterIntake1)
    suspend fun getAllWaterIntake(): List<WaterIntake1>
    suspend fun deleteWaterIntakeByDate(date: String)
}

class WaterIntakeDaoImpl @Inject constructor(
    private val realm: Realm
) : WaterIntakeDao1 {
    override suspend fun createWaterIntake(waterIntake: WaterIntake1) {
        realm.write {
            copyToRealm(waterIntake)
        }
    }

    override suspend fun getWaterIntakeByDate(date: String): WaterIntake1? {
        return realm.query<WaterIntake1>("date == $0", date).first().find()
    }

    override suspend fun updateWaterIntake(waterIntake: WaterIntake1) {
        realm.write {
            copyToRealm(waterIntake)
        }
    }

    override suspend fun getAllWaterIntake(): List<WaterIntake1> {
        return realm.query<WaterIntake1>().find()
    }

    override suspend fun deleteWaterIntakeByDate(date: String) {
        realm.write {
            val waterIntake = realm.query<WaterIntake1>("date == $0", date).first().find()
            if (waterIntake != null) {
                delete(waterIntake)
            }
        }
    }
}