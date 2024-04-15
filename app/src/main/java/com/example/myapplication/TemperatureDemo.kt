package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.getSystemService
import org.w3c.dom.Text

class TemperatureDemo : AppCompatActivity(), SensorEventListener {

    private lateinit var mgr: SensorManager
    private var temp: Sensor ?= null
    private lateinit var text: TextView
    private var msg = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature_demo)

        mgr = getSystemService(SENSOR_SERVICE) as SensorManager
        temp = mgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        text = findViewById(R.id.textView2)

    }

    override fun onResume() {
        super.onResume()
        mgr.registerListener(this, temp,
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mgr.unregisterListener(this, temp)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val fahrenheit = event.values[0] * 9 / 5 + 32
            msg.insert(0,"Got a sensor event: "+
                    "${event.values[0]} Celsuis ($fahrenheit F)\n")

            text.text = msg
            text.invalidate()
        }
    }

}