package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val c = CustomViewFan(this)
//        setContentView(c)
        val g = GameViewDemo(this)
        setContentView(g)
    }
}