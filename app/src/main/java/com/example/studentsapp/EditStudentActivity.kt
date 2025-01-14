package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    private val repository = Model.instance
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val nameEditText: EditText = findViewById(R.id.editStudentName)
        val idEditText: EditText = findViewById(R.id.editStudentId)
        val phoneEditText: EditText = findViewById(R.id.editStudentPhone)
        val addressEditText: EditText = findViewById(R.id.editStudentAddress)
        val saveButton: Button = findViewById(R.id.saveStudentButton)

        // Retrieve the student position
        position = intent.getIntExtra("STUDENT_POSITION", -1)

        if (position != -1) {
            val student = repository.getAllStudents()[position]

            // Populate fields with existing data
            nameEditText.setText(student.name)
            idEditText.setText(student.id)
            phoneEditText.setText(student.phone)
            addressEditText.setText(student.address)

            saveButton.setOnClickListener {
                // Update student data
                student.name = nameEditText.text.toString()
                student.id = idEditText.text.toString()
                student.phone = phoneEditText.text.toString()
                student.address = addressEditText.text.toString()

                finish() // Close the activity
            }
        }
    }
}