package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MainActivity10 : AppCompatActivity() {
    lateinit var latitude: TextView
    lateinit var longitude: TextView
    lateinit var country: TextView
    lateinit var locality: TextView
    lateinit var address: TextView
    lateinit var btnSubmit: Button
    lateinit var btnOpenMap: Button
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)

        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longtitude)
        country = findViewById(R.id.country)
        locality = findViewById(R.id.locality)
        address = findViewById(R.id.address)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnOpenMap = findViewById(R.id.btnOpenMap)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        btnSubmit.setOnClickListener {
            getLocation()
        }

        btnOpenMap.setOnClickListener {
            Handler().postDelayed({
                val gmmIntentUri = Uri.parse("geo:0,0?q=")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }, 1000)
        }
    }

    private fun getLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnSuccessListener {
                        location: Location? ->
                    location?.let {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val list: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)!!
                        latitude.text = "Latitude\n${list[0].latitude}"
                        longitude.text = "Longitude\n${list[0].longitude}"
                        country.text = "Country Name\n${list[0].countryName}"
                        locality.text = "Locality\n${list[0].locality}"
                        address.text = "Address\n${list[0].getAddressLine(0)}"
                    }
                }
            }
            else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), permissionId)
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
}