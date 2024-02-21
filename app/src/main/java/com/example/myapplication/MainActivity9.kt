package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity9 : AppCompatActivity() {
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            startActivity(Intent(this, MainActivity8::class.java))
//            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
//            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out)
            overridePendingTransition(R.anim.sequential, R.anim.zoom_out)
        }
    }
}