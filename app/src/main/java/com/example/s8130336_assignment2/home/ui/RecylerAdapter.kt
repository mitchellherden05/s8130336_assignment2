package com.example.s8130336_assignment2.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130336_assignment2.home.data.CourseDataClass

class RecylerAdapter(private var courseList: List<CourseDataClass> = listOf())
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {

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

        fun updateData(newCourseList: List<CourseDataClass>) {
            courseList = newCourseList
            notifyDataSetChanged()
        }


}