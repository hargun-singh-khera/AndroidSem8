package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AcclGyrDemo : AppCompatActivity() {

    private lateinit var accelerometer: Accelerometer
    private lateinit var gyroscope: Gyroscope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accl_gyr_demo)

        accelerometer = Accelerometer(this)
        gyroscope = Gyroscope(this)

        accelerometer.setListener(object : Accelerometer.Listener {
            override fun onTranslation(tx: Float, ty: Float, ts: Float) {
                if(tx > 1.0f) {
                    window.decorView.setBackgroundColor(Color.RED)
                } else if (tx < -1.0f) {
                    window.decorView.setBackgroundColor(Color.BLUE)
                }
            }
        })


        gyroscope.setListener(object : Gyroscope.Listener {
            override fun onRotation(rx: Float, ry: Float, rz: Float) {
                if (rz > 1.0f) {
                    window.decorView.setBackgroundColor(Color.GREEN)
                } else if (rz < -1.0f) {
                    window.decorView.setBackgroundColor(Color.YELLOW)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        accelerometer.register()
        gyroscope.register()
    }

    override fun onPause() {
        super.onPause()
        accelerometer.unregister()
        gyroscope.unregister()
    }

}