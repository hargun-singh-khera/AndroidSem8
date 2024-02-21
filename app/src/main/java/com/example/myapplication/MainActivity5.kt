package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class MainActivity5 : AppCompatActivity() {
    lateinit var iv:ImageView
    lateinit var btn: Button
    lateinit var animBlink: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        iv = findViewById(R.id.iv)
        btn = findViewById(R.id.btn)

//        val s = AlphaAnimation(0.0f, 1.0f)
//        s.duration = 50
//        s.repeatMode = Animation.REVERSE
//        s.repeatCount = Animation.INFINITE
//        s.startOffset = 20
//        iv.startAnimation(s)

        btn.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            iv.startAnimation(animBlink)
        }

    }
}