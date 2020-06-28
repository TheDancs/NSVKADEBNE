package com.iwkzh4.smarthome.model

data class CumulatedDataResponse(
    val sensorData: List<AvgSensorData>,
    val timeStamp: String
)