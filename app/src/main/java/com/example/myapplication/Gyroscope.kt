package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class Gyroscope internal constructor(context: Context) {
    interface Listener {
        fun onRotation(tx: Float, ty: Float, ts: Float)
    }

    private var listener: Listener? = null
    fun setListener(l: Listener?) {
        listener = l
    }

    private val sensorManager: SensorManager
    private val sensor: Sensor?
    private val sensorEventListener: SensorEventListener

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                if (listener != null) {
                    // pass the three floats in listener on rotation of axis
                    listener!!.onRotation(
                        sensorEvent.values[0], sensorEvent.values[1],
                        sensorEvent.values[2]
                    )
                }
            }

            override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
        }
    }

    fun register() {
        sensorManager.registerListener(
            sensorEventListener,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun unregister() {
        sensorManager.unregisterListener(sensorEventListener)
    }
}