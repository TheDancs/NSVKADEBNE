package com.iwkzh4.smarthome.model

data class Sensor(
    val friendlyName: String,
    val id: String,
    val type: String,
    val unit: String,
    val value: Double
)