package com.iwkzh4.smarthome.model

data class SensorResponse(
    val timeStamp: String,
    val value: List<Sensor>
)