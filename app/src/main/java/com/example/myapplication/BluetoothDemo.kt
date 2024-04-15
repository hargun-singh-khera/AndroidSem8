package com.example.myapplication

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class BluetoothDemo : AppCompatActivity() {
    lateinit private var lstvw: ListView
    private var aAdapter: ArrayAdapter<*>? = null
    private val bAdapter = BluetoothAdapter.getDefaultAdapter()
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_demo)
        val btn: Button = findViewById<View>(R.id.btnGet) as Button

        btn.setOnClickListener {
            if (bAdapter == null) {
                Toast.makeText(applicationContext, "Bluetooth Not Supported", Toast.LENGTH_SHORT).show()
            } else {
                val pairedDevices = bAdapter.bondedDevices
                val list = ArrayList<Any>()
                if (pairedDevices.size > 0) {
                    for (device in pairedDevices) {
                        val devicename = device.name
                        val macAddress = device.address
                        list.add("Name: " + devicename + "MAC Address: " + macAddress)
                    }
                    lstvw = findViewById<View>(R.id.deviceList) as ListView
                    aAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
                    lstvw.adapter=aAdapter
                }
            }
        }
    }
}