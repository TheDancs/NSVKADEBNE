package com.iwkzh4.smarthome.ui.devices

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.iwkzh4.smarthome.api.DevicesApi
import com.iwkzh4.smarthome.data.RecentDevice
import com.iwkzh4.smarthome.data.RecentDevicesDb
import com.iwkzh4.smarthome.model.Device
import com.iwkzh4.smarthome.repositories.DeviceRepository
import com.iwkzh4.smarthome.repositories.RecentDevicesRepository
import com.iwkzh4.smarthome.utils.Coroutines
import com.iwkzh4.smarthome.utils.DeviceItemClickListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DevicesViewModel(application: Application) : AndroidViewModel(application),
    DeviceItemClickListener {

    private lateinit var job: Job
    private var devicesRepository: DeviceRepository
    private var recentDevicesRepository: RecentDevicesRepository

    private val _devices = MutableLiveData<List<Device>>()
    val devices: LiveData<List<Device>>
        get() = _devices

    var searchText: String = ""

    init {
        val api = DevicesApi()
        devicesRepository = DeviceRepository(api)
        val dataSource = RecentDevicesDb.getInstance(application).recentDevicesDao()
        recentDevicesRepository = RecentDevicesRepository(dataSource)

        getSensors()
    }

    private fun getSensors() {
        job = Coroutines.workThenMain(
            { devicesRepository.getDevices() },
            {
                if (it?.value != null) {
                    _devices.value = it.value
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

            val recentDevices = recentDevicesRepository.getRecentDevices()

            if (recentDevices.size < 9 || recentDevices.any { it.id == device.id }) {
                addRecentDevice(device)
            } else {
                recentDevicesRepository.deleteDevice(recentDevices.first())
                addRecentDevice(device)
            }
        }
    }

    private suspend fun addRecentDevice(device: Device) {
        recentDevicesRepository.addDevice(
            RecentDevice(
                device.id,
                device.type,
                device.friendlyName
            )
        )
    }
}