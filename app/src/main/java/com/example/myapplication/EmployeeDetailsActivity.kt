package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class EmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpName: TextView
    private lateinit var tvEmpAge: TextView
    private lateinit var tvEmpSalary: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvEmpAge = findViewById(R.id.tvEmpAge)
        tvEmpSalary = findViewById(R.id.tvEmpSalary)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

        setValuesToViews()

        btnUpdate.setOnClickListener {
            val intent = Intent(this, EmployeeDetailsUpdateActivity::class.java)
            intent.putExtra("empId", tvEmpId.text.toString())
            intent.putExtra("empName", tvEmpName.text.toString())
            intent.putExtra("empAge", tvEmpAge.text.toString())
            intent.putExtra("empSalary", tvEmpSalary.text.toString())
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
//            deleteRecord(intent.getStringExtra("empId").toString())
            deleteConfirmAlertDialogBox()
        }
    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpName.text = intent.getStringExtra("empName")
        tvEmpAge.text = intent.getStringExtra("empAge")
        tvEmpSalary.text = intent.getStringExtra("empSalary")
    }

    private fun deleteRecord(id: String ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val mTask = dbRef.removeValue()
        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ShowActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteConfirmAlertDialogBox() {
        val builder = AlertDialog.Builder(this)
            .setTitle("Confirm Deletion")
            .setMessage("Are you sure to delete this record?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                deleteRecord(intent.getStringExtra("empId").toString())
                finish()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
        val alertDialog = builder.create()
        alertDialog.show()
    }
}