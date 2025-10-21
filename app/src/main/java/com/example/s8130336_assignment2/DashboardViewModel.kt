package com.example.s8130336_assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8130336_assignment2.home.data.CourseDataClass
import com.example.s8130336_assignment2.home.data.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CourseResult {
    data class Success(val courses: List<CourseDataClass>) : CourseResult()
    data class Error(val message: String) : CourseResult()
    object Loading : CourseResult()
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _courseResult = MutableLiveData<CourseResult>()
    val courseResult: LiveData<CourseResult> = _courseResult

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            _courseResult.value = CourseResult.Loading
            try {
                val courses = courseRepository.getAllCourses()
                _courseResult.value = CourseResult.Success(courses)
            } catch (e: Exception) {
                _courseResult.value = CourseResult.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
