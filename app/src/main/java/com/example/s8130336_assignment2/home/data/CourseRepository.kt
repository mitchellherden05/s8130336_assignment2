package com.example.s8130336_assignment2.home.data

import com.example.s8130336_assignment2.home.network.ApiService
import javax.inject.Inject

// Hilt will know how to create an ApiService, so it can provide it to our repository
class CourseRepository @Inject constructor(private val apiService: ApiService) {

    // This function calls the ApiService to get the courses from the network.
    // It's a suspend function to ensure it's called from a coroutine.
    suspend fun getAllCourses(): List<CourseDataClass> {
        return apiService.getAllCourses()
    }
}