package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity3 : AppCompatActivity() {
    lateinit var u:UserDetail
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    lateinit var nameuser: EditText
    lateinit var phnum: EditText
    lateinit var address: EditText
    lateinit var employeeInfo: EmployeeInfo
    lateinit var firebaseDatabase :FirebaseDatabase
    lateinit var databaseReference:DatabaseReference
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: TextView
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        etEmail = findViewById(R.id.etSEmailAddress)
        etConfPass = findViewById(R.id.etSConfPassword)
        etPass = findViewById(R.id.etSPassword)
        nameuser=findViewById(R.id.etSetName)
        phnum=findViewById(R.id.econtactnum)
        address=findViewById(R.id.enterAddress)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        employeeInfo= EmployeeInfo()
        u=UserDetail()
        firebaseDatabase=FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("EmployeeInfo")
        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()
        val n=nameuser.text.toString()
        val a=address.text.toString()
        val c=phnum.text.toString()

        if (email.isEmpty() || pass.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Email and Password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        // If all credential are correct We call createUserWithEmailAndPassword using auth object and pass the email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up"+it.exception, Toast.LENGTH_SHORT).show()
                addDatatoFirebase(n, c, a)
            } else {
                Log.d("Debug Error", it.exception.toString())
                Toast.makeText(this, "Singed Up Failed!"+it.exception, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun addDatatoFirebase(n: String, c: String, a: String) {
        employeeInfo.setEmployeeName(n)
        employeeInfo.setEmployeeContactNumber(c)
        employeeInfo.setEmployeeAddress(a)
        val postListener = object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                databaseReference.setValue(employeeInfo)
                // after adding this data we are showing toast message.
                Toast.makeText(applicationContext, "data added", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                Toast.makeText(applicationContext, "Fail to add data " + databaseError, Toast.LENGTH_SHORT).show()
            }
        }
        databaseReference.addValueEventListener(postListener)
    }
}