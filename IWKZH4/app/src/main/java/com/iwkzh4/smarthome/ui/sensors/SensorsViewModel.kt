package com.iwkzh4.smarthome.ui.sensors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iwkzh4.smarthome.api.SensorApi
import com.iwkzh4.smarthome.model.AvgSensorData
import com.iwkzh4.smarthome.model.Sensor
import com.iwkzh4.smarthome.repositories.SensorRepository
import com.iwkzh4.smarthome.utils.Coroutines
import kotlinx.coroutines.Job

class SensorsViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var job: Job

    private val sensorRepository: SensorRepository
    private val _sensors = MutableLiveData<List<Sensor>>()

    val sensors: LiveData<List<Sensor>>
        get() = _sensors

    var searchText: String = ""

    init {
        val api = SensorApi()
        sensorRepository = SensorRepository(api)

        getSensors()
    }

    private fun getSensors() {
        job = Coroutines.workThenMain(
            { sensorRepository.getSensors() },
            {
                if (it?.value != null) {
                    _sensors.value = it.value
                }
            })
    }

    override fun onCleared() {
        if (::job.isInitialized) {
            job.cancel()
        }
        super.onCleared()
    }
}