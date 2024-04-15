package com.example.myapplication

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BluetoothEnableDisable : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_enable_disable)

        val btnGet = findViewById<Button>(R.id.btnGet)
        val tv = findViewById<TextView>(R.id.tv)

        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        btnGet.setOnClickListener {
            if (mBluetoothAdapter.isEnabled) {
                mBluetoothAdapter.disable()
                tv.text = "Bluetooth is OFF"
            } else {
                mBluetoothAdapter.enable()
                tv.text = "Bluetooth is ON"
            }
        }
    }
}