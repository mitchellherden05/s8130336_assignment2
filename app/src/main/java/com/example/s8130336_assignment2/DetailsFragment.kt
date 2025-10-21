package com.example.s8130336_assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var courseCodeTextView: TextView
    private lateinit var courseNameTextView: TextView
    private lateinit var instructorTextView: TextView
    private lateinit var creditsTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.detailsProgressBar)
        errorTextView = view.findViewById(R.id.detailsErrorTextView)
        courseCodeTextView = view.findViewById(R.id.detailsCourseCode)
        courseNameTextView = view.findViewById(R.id.detailsCourseName)
        instructorTextView = view.findViewById(R.id.detailsInstructor)
        creditsTextView = view.findViewById(R.id.detailsCredits)
        descriptionTextView = view.findViewById(R.id.detailsDescription)


        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.courseDetailsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is CourseDetailsResult.Loading -> {
                    progressBar.isVisible = true
                    errorTextView.isVisible = false
                    hideDetails()
                }
                is CourseDetailsResult.Success -> {
                    progressBar.isVisible = false
                    errorTextView.isVisible = false
                    showDetails(result.courseDetails)
                }
                is CourseDetailsResult.Error -> {
                    progressBar.isVisible = false
                    errorTextView.isVisible = true
                    errorTextView.text = result.message
                    hideDetails()
                }
            }
        }
    }

    private fun showDetails(details: com.example.s8130336_assignment2.home.data.CourseDetailsClass) {
        courseCodeTextView.text = details.courseCode ?: "N/A"
        courseNameTextView.text = details.courseName ?: "Unknown Course"
        instructorTextView.text = details.instructor ?: "Unknown Instructor"
        creditsTextView.text = "Credits: " + details.credits?.toString() ?: "N/A"
        descriptionTextView.text = details.description ?: "No description available."
        courseCodeTextView.isVisible = true
        courseNameTextView.isVisible = true
        instructorTextView.isVisible = true
        creditsTextView.isVisible = true
        descriptionTextView.isVisible = true
    }

    private fun hideDetails() {
        courseCodeTextView.isVisible = false
        courseNameTextView.isVisible = false
        instructorTextView.isVisible = false
        creditsTextView.isVisible = false
        descriptionTextView.isVisible = false
    }
}
