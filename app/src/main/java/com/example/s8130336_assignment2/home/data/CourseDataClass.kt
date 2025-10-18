package com.example.s8130336_assignment2.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDataClass(
    @Json(name = "courseCode") var course_code: String,
    @Json(name = "courseName") var course_name: String,
    @Json(name = "instructor") var instructor: String
)

@JsonClass(generateAdapter = true)
data class Details(
    @Json(name = "credits") var course_credits: String,
    @Json(name = "description") var course_description: String
)
