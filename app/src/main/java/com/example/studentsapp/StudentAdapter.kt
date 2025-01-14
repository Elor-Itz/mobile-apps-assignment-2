package com.example.studentsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Student

class StudentAdapter(
    private val students: List<Student>,
    private val listener: OnStudentClickListener
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    interface OnStudentClickListener {
        fun onStudentClick(position: Int)
        fun onCheckChanged(position: Int, isChecked: Boolean)
    }

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.studentName)
        val phoneTextView: TextView = view.findViewById(R.id.studentPhone)
        val checkbox: CheckBox = view.findViewById(R.id.studentCheckBox)

        init {
            view.setOnClickListener {
                listener.onStudentClick(adapterPosition)
            }

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                listener.onCheckChanged(adapterPosition, isChecked)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTextView.text = student.name
        holder.phoneTextView.text = student.phone
        holder.checkbox.isChecked = student.cb
    }

    override fun getItemCount(): Int = students.size
}