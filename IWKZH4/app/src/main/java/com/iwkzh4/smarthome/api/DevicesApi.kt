package com.iwkzh4.smarthome.api

import com.iwkzh4.smarthome.model.DeviceStateChangeResponse
import com.iwkzh4.smarthome.model.DeviceStatus
import com.iwkzh4.smarthome.model.DevicesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DevicesApi {
    @GET("devices")
    suspend fun getDevices(): Response<DevicesResponse>

    @POST("devices/{id}/switch")
    suspend fun postDeviceStatus(@Path("id") id: String): Response<DeviceStateChangeResponse>

    @GET("devices/{id}/state")
    suspend fun getDeviceStatus(@Path("id") id: String): Response<DeviceStatus>

    companion object {
        operator fun invoke(): DevicesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://kotlinexam.azurewebsites.net/api/")
                .build()
                .create(DevicesApi::class.java)
        }
    }
}

