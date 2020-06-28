package com.iwkzh4.smarthome.repositories

import com.iwkzh4.smarthome.api.DevicesApi
import com.iwkzh4.smarthome.model.DeviceStatus
import com.iwkzh4.smarthome.utils.SafeApiRequest
import retrofit2.await

class DeviceRepository(private val api: DevicesApi) : SafeApiRequest() {

    suspend fun getDevices() = apiRequest { api.getDevices() }
    suspend fun changeDeviceStatus(id: String) = apiRequest { api.postDeviceStatus(id) }
    suspend fun getDeviceStatus(id: String) = apiRequest { api.getDeviceStatus(id) }
}