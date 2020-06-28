package com.iwkzh4.smarthome.data

import androidx.room.*

@Dao
interface RecentDevicesDao {
    @Query("SELECT * FROM recent_devices")
    suspend fun getRecentDevices(): List<RecentDevice>

    @Delete
    suspend fun deleteRecentDevice(device: RecentDevice)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateDevice(device: RecentDevice)
}