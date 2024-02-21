package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var a:FirebaseAuth
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var cpass: EditText
    lateinit var btnSignUp: Button
    lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        cpass = findViewById(R.id.cpass)
        btnSignUp = findViewById(R.id.signUpBtn)
        btnLogin = findViewById(R.id.loginBtn)
        a = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            if(email.text.toString().isEmpty() || pass.text.toString().isEmpty() || cpass.text.toString().isEmpty()) {
                Toast.makeText(this, "All fields are mandatory!", Toast.LENGTH_SHORT).show()
            }
            else if(pass.text.toString() != cpass.text.toString()) {
                Toast.makeText(this, "Password and Confirm Password doesn't matches", Toast.LENGTH_SHORT).show()
            }
            else {
                signUp()
            }
        }

        btnLogin.setOnClickListener {
            login()
        }
    }

    fun signUp() {
        a.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString()).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this@MainActivity, "Sign Up Done", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@MainActivity, "Sign Up Not Done "+it.exception, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login() {
        a.signInWithEmailAndPassword(email.text.toString(), pass.text.toString()).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this@MainActivity, "Login Done", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@MainActivity, "Login Not Done", Toast.LENGTH_SHORT).show()
            }
        }
    }
}