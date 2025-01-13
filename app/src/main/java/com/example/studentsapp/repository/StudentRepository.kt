package com.example.studentsapp.repository

import com.example.studentsapp.model.Student

class StudentRepository private constructor() {

    private val students: MutableList<Student> = mutableListOf()

    // Get students
    fun getStudents(): List<Student> = students

    // Add student
    fun addStudent(student: Student) {
        students.add(student)
    }

    // Update student
    fun updateStudent(position: Int, student: Student) {
        students[position] = student
    }

    // Delete student
    fun deleteStudent(position: Int) {
        students.removeAt(position)
    }

    companion object {
        private var instance: StudentRepository? = null

        fun getInstance(): StudentRepository {
            if (instance == null) {
                instance = StudentRepository()
            }
            return instance!!
        }
    }
}