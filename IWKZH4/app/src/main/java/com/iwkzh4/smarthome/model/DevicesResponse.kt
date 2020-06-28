package com.iwkzh4.smarthome.model

data class DevicesResponse(
    val timeStamp: String,
    val value: List<Device>
)