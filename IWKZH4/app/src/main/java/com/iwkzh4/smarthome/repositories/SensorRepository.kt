package com.iwkzh4.smarthome.repositories

import com.iwkzh4.smarthome.api.SensorApi
import com.iwkzh4.smarthome.model.AvgSensorData
import com.iwkzh4.smarthome.utils.SafeApiRequest

class SensorRepository(private val api: SensorApi) : SafeApiRequest() {

    suspend fun getCumulatedSensorValues() = apiRequest { api.getCumulatedData() }
    suspend fun getSensors() = apiRequest { api.getSensors() }
}