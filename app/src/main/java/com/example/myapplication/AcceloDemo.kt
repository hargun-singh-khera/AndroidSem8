package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AcceloDemo : AppCompatActivity() {
    private var sm: SensorManager?= null
    private var textView: TextView ?= null
    private var list: List<Sensor> ?= null
    private val sel: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent?) {
            val values = event!!.values
            textView?.text = "x: ${values[0]}\ny: ${values[1]}\nz: ${values[2]}"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelo_demo)
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        textView = findViewById(R.id.textView)
        list = sm?.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if (list?.isNotEmpty() == true) {
            sm?.registerListener(sel, list?.get(0), SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(this, "Error: No Acceleromoter", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        if (list?.isNotEmpty() == true) {
            sm?.unregisterListener(sel)
        }
        super.onStop()
    }
}