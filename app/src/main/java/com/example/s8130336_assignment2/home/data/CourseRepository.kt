package com.example.s8130336_assignment2.home.data

import com.example.s8130336_assignment2.home.network.ApiService
import javax.inject.Inject

// Hilt will know how to create an ApiService, so it can provide it to our repository
class CourseRepository @Inject constructor(private val apiService: ApiService) {

    // This function calls the ApiService to get the courses from the network.
    // It's a suspend function to ensure it's called from a coroutine.
    suspend fun getAllCourses(): List<CourseDataClass> {
        // We get the response object and then return the list of courses inside it.
        return apiService.getAllCourses().courses
    }

    suspend fun getCourseDetails(courseCode: String): CourseDetailsClass {
        // The server returns a list inside an "entities" object, even for a single course.
        val response = apiService.getCourseDetails(courseCode)
        // We must extract the first item from that list.
        return response.courses.firstOrNull()
            ?: throw NoSuchElementException("Course with code $courseCode not found in the server response.")
    }
}
