package com.example.s8130336_assignment2.home.network

import com.example.s8130336_assignment2.home.data.CourseDetailsListResponse
import com.example.s8130336_assignment2.home.data.CourseListResponse
import com.example.s8130336_assignment2.home.data.LoginRequest
import com.example.s8130336_assignment2.home.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("dashboard/courses")
    suspend fun getAllCourses(): CourseListResponse

    @GET("dashboard/courses")
    suspend fun getCourseDetails(@Query("course_code") courseCode: String): CourseDetailsListResponse

    @POST("footscray/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
