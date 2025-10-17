package com.example.s8130336_assignment2.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDataClass(
    @Json(name = "courseCode") val course_code: String,
    @Json(name = "courseName") val course_name: String,
    @Json(name = "instructor") val instructor: String
)

@JsonClass(generateAdapter = true)
data class Details(
    @Json(name = "credits") val course_credits: String,
    @Json(name = "description") val course_description: String
)
