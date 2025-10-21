package com.example.s8130336_assignment2.home.network

import com.example.s8130336_assignment2.home.data.CourseDataClass
import com.example.s8130336_assignment2.home.data.LoginRequest
import com.example.s8130336_assignment2.home.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("dashboard/courses")
    suspend fun getAllCourses(): List<CourseDataClass>

    @POST("footscray/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
