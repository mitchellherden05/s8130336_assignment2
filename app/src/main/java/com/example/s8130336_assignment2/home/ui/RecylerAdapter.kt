package com.example.s8130336_assignment2.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130336_assignment2.R
import com.example.s8130336_assignment2.home.data.CourseDataClass

class RecylerAdapter(
    private var courseList: List<CourseDataClass> = listOf(),
    private val onDetailsClick: (CourseDataClass) -> Unit
) : RecyclerView.Adapter<RecylerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseCodeTextView: TextView = itemView.findViewById(R.id.courseCodeTextView)
        private val courseNameTextView: TextView = itemView.findViewById(R.id.courseNameTextView)
        private val instructorTextView: TextView = itemView.findViewById(R.id.instructorTextView)
        private val detailsButton: Button = itemView.findViewById(R.id.detailsButton)

        fun bind(course: CourseDataClass) {
            courseCodeTextView.text = course.courseCode ?: "N/A"
            courseNameTextView.text = course.courseName ?: "Unknown Course"
            instructorTextView.text = course.instructor ?: "Unknown Instructor"
            detailsButton.setOnClickListener {
                onDetailsClick(course)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.course_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    fun updateCourses(newCourseList: List<CourseDataClass>) {
        courseList = newCourseList
        notifyDataSetChanged()
    }
}
