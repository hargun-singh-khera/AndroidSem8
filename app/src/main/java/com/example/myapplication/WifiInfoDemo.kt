package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.text.format.Formatter

class WifiInfoDemo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_info_demo)

        val wifiInformationTv: TextView = findViewById(R.id.textViewWifi)

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val wInfo = wifiManager.connectionInfo

        val ipaddress = Formatter.formatIpAddress(wInfo.ipAddress)
        val linkSpeed = wInfo.linkSpeed
        val networkID = wInfo.networkId

        val ssid      = wInfo.ssid
        val hssid     = wInfo.hiddenSSID
        val bssid     = wInfo.bssid

        wifiInformationTv.text = "IP Address : \t$ipaddress\n"+
                "Link Speed : \t$linkSpeed\n"+
                "Network ID : \t$networkID\n"+
                "SSID : \t$ssid\n"+
                "Hidden SSID: \t$hssid\n"+
                "BSSID : \t$bssid\n"

    }
}