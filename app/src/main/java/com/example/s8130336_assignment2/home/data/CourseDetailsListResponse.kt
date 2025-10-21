package com.example.s8130336_assignment2.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDetailsListResponse(
    @Json(name = "entities")
    val courses: List<CourseDetailsClass>
)
