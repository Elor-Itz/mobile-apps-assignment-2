package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentDetailsActivity : AppCompatActivity() {

    private val repository = Model.instance
    private var position: Int = -1

    // Launcher for EditStudentActivity
    private val editStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val updatedStudent = result.data?.getParcelableExtra<Student>("UPDATED_STUDENT")
            val studentPosition = result.data?.getIntExtra("STUDENT_POSITION", -1)

            if (updatedStudent != null && studentPosition != null && studentPosition != -1) {
                // Update repository
                repository.getAllStudents()[studentPosition] = updatedStudent

                // Refresh UI
                displayStudentDetails(updatedStudent)
                Toast.makeText(this, "Details updated successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to update details!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val nameTextView: TextView = findViewById(R.id.studentDetailsName)
        val idTextView: TextView = findViewById(R.id.studentDetailsId)
        val phoneTextView: TextView = findViewById(R.id.studentDetailsPhone)
        val addressTextView: TextView = findViewById(R.id.studentDetailsAddress)
        val editButton: Button = findViewById(R.id.editStudentButton)

        // Retrieve the student position
        position = intent.getIntExtra("STUDENT_POSITION", -1)

        if (position != -1) {
            val student = repository.getAllStudents()[position]
            displayStudentDetails(student)

            editButton.setOnClickListener {
                // Launch EditStudentActivity
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("STUDENT_POSITION", position)
                }
                editStudentLauncher.launch(intent)
            }
        }
    }

    private fun displayStudentDetails(student: Student) {
        findViewById<TextView>(R.id.studentDetailsName).text = student.name
        findViewById<TextView>(R.id.studentDetailsId).text = student.id
        findViewById<TextView>(R.id.studentDetailsPhone).text = student.phone
        findViewById<TextView>(R.id.studentDetailsAddress).text = student.address
    }
}