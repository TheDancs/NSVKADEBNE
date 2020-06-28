package com.iwkzh4.smarthome.utils

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.iwkzh4.smarthome.R

@BindingAdapter("sensorImage")
fun loadSensorImage(view: ImageView, type: String) {
    when (type) {
        "temperature" -> view.setImageResource(R.drawable.ic_hot)
        "humidity" -> view.setImageResource(R.drawable.ic_humidity)
        "co2" -> view.setImageResource(R.drawable.ic_co2)
        "pressure" -> view.setImageResource(R.drawable.ic_pressure)
        "powerConsumption" -> view.setImageResource(R.drawable.ic_bolt)
    }
}

@BindingAdapter("deviceImage")
fun loadDeviceImage(view: ImageView, type: String) {
    when (type) {
        "light" -> view.setImageResource(R.drawable.ic_lightbulb)
        "humidifier" -> view.setImageResource(R.drawable.ic_humidifier)
        "airConditioner" -> view.setImageResource(R.drawable.ic_air_conditioner_2)
        "heater" -> view.setImageResource(R.drawable.ic_heater)
        "socket" -> view.setImageResource(R.drawable.ic_plug)
    }
}

@BindingAdapter("deviceStatus")
fun setDeviceStatus(textView: TextView, status: Boolean) {
    textView.text = if (status) "ON" else "OFF"
    if (status) {
        textView.setTextColor(Color.parseColor("#1AA23F"))
    } else {
        textView.setTextColor(Color.parseColor("#FF6464"))
    }

}