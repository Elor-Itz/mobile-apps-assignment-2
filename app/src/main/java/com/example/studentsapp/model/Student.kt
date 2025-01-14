package com.example.studentsapp.model

data class Student(
    var name: String,
    var id: String,
    var imageUrl: String,
    var phone: String,
    var address: String,
    var cb: Boolean
)