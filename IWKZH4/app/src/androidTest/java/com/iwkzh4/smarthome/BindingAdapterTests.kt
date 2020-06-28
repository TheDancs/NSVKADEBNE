package com.iwkzh4.smarthome

import android.graphics.Color
import android.widget.TextView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iwkzh4.smarthome.utils.setDeviceStatus

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BindingAdapterTests {

    @Test
    fun test_setDeviceStatus()
    {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val textView = TextView(appContext)

        setDeviceStatus(textView, false)

        assert(textView.text == "OFF")
        assert(textView.currentTextColor == Color.parseColor("#FF6464"))
    }
}
