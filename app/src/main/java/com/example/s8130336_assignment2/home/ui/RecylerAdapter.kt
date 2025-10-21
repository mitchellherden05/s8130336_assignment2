package com.example.s8130336_assignment2.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130336_assignment2.R
import com.example.s8130336_assignment2.home.data.CourseDataClass

// The class signature is now corrected to reference its own ViewHolder
class RecylerAdapter(private var courseList: List<CourseDataClass> = listOf())
    : RecyclerView.Adapter<RecylerAdapter.ViewHolder>() {

    /**
     * The ViewHolder's job is to find the views in the layout once and hold them.
     * This avoids expensive `findViewById` calls for every single list item.
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // --- IMPORTANT ---
        // Make sure these IDs match the IDs in your `course_item.xml` layout file.
        private val courseCodeTextView: TextView = itemView.findViewById(R.id.courseCodeTextView)
        private val courseNameTextView: TextView = itemView.findViewById(R.id.courseNameTextView)
        private val instructorTextView: TextView = itemView.findViewById(R.id.instructorTextView)

        /**
         * The bind function takes a CourseDataClass object and sets the data
         * on the views held by this ViewHolder.
         */
        fun bind(course: CourseDataClass) {
            courseCodeTextView.text = course.course_code
            courseNameTextView.text = course.course_name
            instructorTextView.text = course.instructor
        }
    }

    // This must return your custom ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.course_item, parent, false)
        return ViewHolder(view)
    }

    // The `holder` parameter is now correctly typed to your custom ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Call the bind function you defined in the ViewHolder.
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
