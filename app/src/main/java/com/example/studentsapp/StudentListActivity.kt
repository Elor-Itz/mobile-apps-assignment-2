package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Model
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity(), StudentAdapter.OnStudentClickListener {

    private val repository = Model.instance  // Accessing the singleton Model
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        // Initialize RecyclerView and FloatingActionButton
        val recyclerView: RecyclerView = findViewById(R.id.studentRecyclerView)
        val addButton: FloatingActionButton = findViewById(R.id.addStudentButton)

        // Set up RecyclerView with LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(repository.getAllStudents(), this)
        recyclerView.adapter = adapter

        // Handle Add Button click
        addButton.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onStudentClick(position: Int) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("STUDENT_POSITION", position)
        startActivity(intent)
    }

    override fun onCheckChanged(position: Int, isChecked: Boolean) {
        val student = repository.getAllStudents()[position]
        student.cb = isChecked
        adapter.notifyItemChanged(position)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}