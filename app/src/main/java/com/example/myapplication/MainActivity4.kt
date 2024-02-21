package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity4 : AppCompatActivity() {
    lateinit var btnSave: Button
    lateinit var btnFetch: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        btnSave = findViewById(R.id.btnSave)
        btnFetch = findViewById(R.id.btnFetch)

        btnSave.setOnClickListener {
            startActivity(Intent(this, SaveActivity::class.java))
        }
        btnFetch.setOnClickListener {
            startActivity(Intent(this, ShowActivity::class.java))
        }
    }
}