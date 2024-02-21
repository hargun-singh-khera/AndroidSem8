package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val tvWelcome: TextView = findViewById(R.id.tvWelcome)
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        tvWelcome.startAnimation(animation)

        val backgroundImage: ImageView = findViewById(R.id.splashScreenImage)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        backgroundImage.startAnimation(slideAnimation)

        val btn: Button = findViewById(R.id.btn1)
        val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
        btn.startAnimation(anim)

        val btn2: Button = findViewById(R.id.btn2)
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.slideslide)
        btn.startAnimation(anim2)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}