package com.example.s8130336_assignment2.home.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDetailsClass(
    val courseCode: String?,
    val courseName: String?,
    val instructor: String?,
    val credits: Int?,
    val description: String?
)
