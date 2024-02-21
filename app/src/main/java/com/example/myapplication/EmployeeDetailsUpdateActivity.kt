package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EmployeeDetailsUpdateActivity : AppCompatActivity() {
    private lateinit var tvEmpId: TextView
    private lateinit var etEmpName: EditText
    private lateinit var etEmpAge: EditText
    private lateinit var etEmpSalary: EditText
    private lateinit var btnUpdate: Button

    lateinit var name: String
    lateinit var age:String
    lateinit var salary:String
    lateinit var id:String

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details_update)

        tvEmpId = findViewById(R.id.tvEmpId)
        etEmpName = findViewById(R.id.tvEmpName)
        etEmpAge = findViewById(R.id.tvEmpAge)
        etEmpSalary = findViewById(R.id.tvEmpSalary)
        btnUpdate = findViewById(R.id.btnUpdate)

        setValuesToViews()

        btnUpdate.setOnClickListener {
            name = etEmpName.text.toString()
            age = etEmpAge.text.toString()
            salary = etEmpSalary.text.toString()
            Toast.makeText(this, "hello "+etEmpName.text.toString(), Toast.LENGTH_LONG).show()
            updateEmpData(tvEmpId.text.toString(), name, age, salary)
        }
    }

    private fun updateEmpData(tvEmpId: String, name: String, age: String, salary: String) {
        dbRef = FirebaseDatabase.getInstance().getReference("Employees")
        var empInfo = EmployeeModel(tvEmpId, name, age, salary)
        dbRef.child(tvEmpId).setValue(empInfo).addOnSuccessListener {
            Toast.makeText(this@EmployeeDetailsUpdateActivity, "Updated Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@EmployeeDetailsUpdateActivity, LoginActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empId")
        etEmpName.setText(intent.getStringExtra("empName"))
        etEmpAge.setText(intent.getStringExtra("empAge"))
        etEmpSalary.setText(intent.getStringExtra("empSalary"))
    }
}