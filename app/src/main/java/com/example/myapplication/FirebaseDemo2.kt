package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FirebaseDemo2 : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var cpass: EditText
    lateinit var btnSignUp: Button
    lateinit var a: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_demo2)

        a = FirebaseAuth.getInstance()
        db = Firebase.database.reference
        db.child("K0202").child("cse227").setValue("unit1")
        db.child("customerMyApp").child("Name").setValue("Amar")
        db.child("customerMyApp").child("Name")
            .child("ContactDetails").child("PersonalNumber").setValue("4555556")

//        a.createUserWithEmailAndPassword(
//            email="batchuamarnathgupta1@gmail.com",
//            password ="amar@143"
//        )

    }
}