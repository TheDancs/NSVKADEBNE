package com.iwkzh4.smarthome.repositories

import com.iwkzh4.smarthome.data.RecentDevice
import com.iwkzh4.smarthome.data.RecentDevicesDao
import com.iwkzh4.smarthome.data.RecentDevicesDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecentDevicesRepository(private val db: RecentDevicesDao) {

    suspend fun getRecentDevices(): List<RecentDevice> {
        return withContext(Dispatchers.IO) {
            db.getRecentDevices()
        }
    }

    suspend fun addDevice(device: RecentDevice) {
        withContext(Dispatchers.IO) {
            db.insertOrUpdateDevice(device)
        }
    }

    suspend fun deleteDevice(device: RecentDevice) {
        withContext(Dispatchers.IO) {
            db.deleteRecentDevice(device)
        }
    }
}