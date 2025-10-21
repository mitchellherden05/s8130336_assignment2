package com.example.s8130336_assignment2.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseListResponse(
    @Json(name = "entities") // The key for the list is "entities"
    val courses: List<CourseDataClass>
)
