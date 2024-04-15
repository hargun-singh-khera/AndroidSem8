package com.example.myapplication

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WifiEnableDisable : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_enable_disable)

        val tv = findViewById<TextView>(R.id.tv)
        val btnEnable = findViewById<Button>(R.id.btnEnable)
        val btnDisable = findViewById<Button>(R.id.btnDisable)

        btnEnable.setOnClickListener {
            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            tv.text = "Wifi Enabled"
            wifiManager.isWifiEnabled = true
        }

        btnDisable.setOnClickListener {
            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            tv.text = "Wifi Disabled"
            wifiManager.isWifiEnabled = false
        }

    }
}