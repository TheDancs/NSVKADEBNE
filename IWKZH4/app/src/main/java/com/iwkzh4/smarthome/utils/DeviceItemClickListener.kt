package com.iwkzh4.smarthome.utils

import android.view.View
import com.iwkzh4.smarthome.model.Device

interface DeviceItemClickListener {
    fun onClick(view: View, device: Device)
}