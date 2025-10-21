package com.example.s8130336_assignment2.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDetailResponse(
    @Json(name = "entity") // Assuming the course object is nested under an "entity" key
    val course: CourseDetailsClass
)
