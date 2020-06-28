package com.iwkzh4.smarthome

import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iwkzh4.smarthome.model.Device
import com.iwkzh4.smarthome.ui.devices.DevicesViewModel
import com.iwkzh4.smarthome.ui.home.HomeViewModel
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DevicesViewModelTests {

    @Test
    fun test_CollectionsAreInitialized() {
        val viewModel = DevicesViewModel(ApplicationProvider.getApplicationContext())

        assert(viewModel.devices.value != null)
    }

    @Test
    fun test_deviceItemClick() {
        val viewModel = DevicesViewModel(ApplicationProvider.getApplicationContext())
        val device = viewModel.devices.value?.firstOrNull()

        if (device!=null){
            val initialStatus = device.status;

            viewModel.onClick(
                ImageView(ApplicationProvider.getApplicationContext()), device)

            val test = viewModel.devices.value?.firstOrNull { it.id === device.id }

            assert(test!=null && test.status == !initialStatus)

        }

        assert(device != null)
    }
}