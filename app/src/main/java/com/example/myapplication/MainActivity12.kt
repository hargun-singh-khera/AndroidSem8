package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.util.Locale


class MainActivity12 : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var etSpeak: EditText
    lateinit var btnSpeak: Button
    private var tts: TextToSpeech? = null
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main12)
        btnSpeak = findViewById(R.id.btn_s)
        etSpeak = findViewById(R.id.edt_t1)
        tts = TextToSpeech(this, this)
        btnSpeak!!.setOnClickListener {
            speakOut()
        }
    }
    override fun onInit(status: Int)
    {
        if(status == TextToSpeech.SUCCESS)
        {
            val result = tts!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS", "The language is not supported!")
            }
            else
            {
                btnSpeak.isEnabled = true
            }
        }
    }

    fun speakOut()
    {
        val text = etSpeak.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null, "")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}