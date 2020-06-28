package com.iwkzh4.smarthome.api

import com.iwkzh4.smarthome.model.AvgSensorData
import com.iwkzh4.smarthome.model.CumulatedDataResponse
import com.iwkzh4.smarthome.model.SensorResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface SensorApi {

    @GET("sensors")
    suspend fun getSensors(): Response<SensorResponse>

    @GET("sensors/cumulated/latest")
    suspend fun getCumulatedData(): Response<CumulatedDataResponse>

    companion object {
        operator fun invoke(): SensorApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://kotlinexam.azurewebsites.net/api/")
                .build()
                .create(SensorApi::class.java)
        }
    }
}