package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private val repository = Model.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val nameTextView: TextView = findViewById(R.id.studentDetailsName)
        val idTextView: TextView = findViewById(R.id.studentDetailsId)
        val phoneTextView: TextView = findViewById(R.id.studentDetailsPhone)
        val addressTextView: TextView = findViewById(R.id.studentDetailsAddress)
        val editButton: Button = findViewById(R.id.editStudentButton)

        // Get the student position from intent
        val position = intent.getIntExtra("STUDENT_POSITION", -1)

        if (position != -1) {
            val student = repository.getAllStudents()[position]

            // Populate student details
            nameTextView.text = student.name
            idTextView.text = student.id
            phoneTextView.text = student.phone
            addressTextView.text = student.address

            // Navigate to EditStudentActivity
            editButton.setOnClickListener {
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("STUDENT_POSITION", position)
                startActivity(intent)
            }
        }
    }
}