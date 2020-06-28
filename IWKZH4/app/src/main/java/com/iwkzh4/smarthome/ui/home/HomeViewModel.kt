package com.iwkzh4.smarthome.ui.home

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.iwkzh4.smarthome.api.DevicesApi
import com.iwkzh4.smarthome.api.SensorApi
import com.iwkzh4.smarthome.data.RecentDevicesDb
import com.iwkzh4.smarthome.model.AvgSensorData
import com.iwkzh4.smarthome.model.Device
import com.iwkzh4.smarthome.repositories.DeviceRepository
import com.iwkzh4.smarthome.repositories.RecentDevicesRepository
import com.iwkzh4.smarthome.repositories.SensorRepository
import com.iwkzh4.smarthome.utils.DeviceItemClickListener
import com.iwkzh4.smarthome.utils.Coroutines
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application),
    DeviceItemClickListener {

    private lateinit var job: Job
    private val sensorRepository: SensorRepository
    private val devicesRepository: DeviceRepository
    private val recentDevicesRepository: RecentDevicesRepository

    private val _latestSensorData = MutableLiveData<List<AvgSensorData>>()
    val latestSensorData: LiveData<List<AvgSensorData>>
        get() = _latestSensorData

    private val _recentDevices = MutableLiveData<List<Device>>()
    val recentDevices: LiveData<List<Device>>
        get() = _recentDevices

    init {
        val api = SensorApi()
        sensorRepository = SensorRepository(api)

        val deviceApi = DevicesApi()
        devicesRepository = DeviceRepository(deviceApi)

        val dataSource = RecentDevicesDb.getInstance(application).recentDevicesDao()
        recentDevicesRepository = RecentDevicesRepository(dataSource)

        initLatestSensorData()
        initializeRecentDevices()
    }

    private fun initializeRecentDevices() {
        viewModelScope.launch {
            val devices = recentDevicesRepository.getRecentDevices()
            val list = ArrayList<Device>()

            devices.forEach {
                val status = devicesRepository.getDeviceStatus(it.id)
                list.add(Device(it.id, it.type, it.friendlyName, status.status))
            }

            _recentDevices.value = list
        }
    }

    private fun initLatestSensorData() {
        job = Coroutines.workThenMain(
            { sensorRepository.getCumulatedSensorValues() },
            {
                if (it != null) {
                    _latestSensorData.value = it.sensorData
                }
            })

    }

    override fun onCleared() {
        if (::job.isInitialized) {
            job.cancel()
        }
        super.onCleared()
    }

    override fun onClick(view: View, device: Device) {

        device.status = !device.status

        viewModelScope.launch {
            devicesRepository.changeDeviceStatus(device.id)
        }
    }
}