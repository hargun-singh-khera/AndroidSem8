package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    lateinit var a: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        a= FirebaseAuth.getInstance()
        a.createUserWithEmailAndPassword("hargunsinghkhera8@gmail.com","123456").addOnCompleteListener{
            a.currentUser?.sendEmailVerification()?.addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this,"Email Sent" + it.exception,Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this,"Not sent and Signup Not done" + it.exception, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
