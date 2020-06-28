package com.iwkzh4.smarthome.model

data class Device(
    val id: String,
    val type: String,
    val friendlyName: String,
    var status: Boolean
)