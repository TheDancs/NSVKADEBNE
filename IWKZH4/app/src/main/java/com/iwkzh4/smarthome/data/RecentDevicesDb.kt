package com.iwkzh4.smarthome.data

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [RecentDevice::class], version = 1, exportSchema = false)
abstract class RecentDevicesDb : RoomDatabase() {
    abstract fun recentDevicesDao(): RecentDevicesDao

    companion object {
        @Volatile
        private var INSTANCE: RecentDevicesDb? = null
        fun getInstance(context: Context): RecentDevicesDb {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecentDevicesDb::class.java,
                        "recent_devices"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}