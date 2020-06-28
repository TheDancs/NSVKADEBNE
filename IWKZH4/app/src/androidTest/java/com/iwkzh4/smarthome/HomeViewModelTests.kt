package com.iwkzh4.smarthome

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iwkzh4.smarthome.ui.home.HomeViewModel
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeViewModelTests {

    @Test
    fun test_collectionInitialized() {
        val viewModel = HomeViewModel(ApplicationProvider.getApplicationContext())

        assert(viewModel.recentDevices.value != null)
        assert(viewModel.latestSensorData.value != null)
    }
}