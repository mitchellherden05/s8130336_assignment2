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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130336_assignment2.home.ui.RecylerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecylerAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        errorTextView = view.findViewById(R.id.errorTextView)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = RecylerAdapter(emptyList()) { course ->
            // Make sure the courseCode is not null before navigating
            course.courseCode?.let {
                val action = DashboardFragmentDirections.actionDashboardFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        viewModel.courseResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is CourseResult.Loading -> {
                    progressBar.isVisible = true
                    errorTextView.isVisible = false
                    recyclerView.isVisible = false
                }
                is CourseResult.Success -> {
                    progressBar.isVisible = false
                    errorTextView.isVisible = false
                    recyclerView.isVisible = true
                    adapter.updateCourses(result.courses)
                }
                is CourseResult.Error -> {
                    progressBar.isVisible = false
                    errorTextView.isVisible = true
                    recyclerView.isVisible = false
                    errorTextView.text = result.message
                }
            }
        }
    }
}
