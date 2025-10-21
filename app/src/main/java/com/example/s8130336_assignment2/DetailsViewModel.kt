package com.example.s8130336_assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8130336_assignment2.home.data.CourseDetailsClass
import com.example.s8130336_assignment2.home.data.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CourseDetailsResult {
    data class Success(val courseDetails: CourseDetailsClass) : CourseDetailsResult()
    data class Error(val message: String) : CourseDetailsResult()
    object Loading : CourseDetailsResult()
}

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _courseDetailsResult = MutableLiveData<CourseDetailsResult>()
    val courseDetailsResult: LiveData<CourseDetailsResult> = _courseDetailsResult

    init {
        val courseCode = savedStateHandle.get<String>("courseCode")
        if (courseCode != null) {
            fetchCourseDetails(courseCode)
        } else {
            _courseDetailsResult.value = CourseDetailsResult.Error("Course code not found.")
        }
    }

    private fun fetchCourseDetails(courseCode: String) {
        viewModelScope.launch {
            _courseDetailsResult.value = CourseDetailsResult.Loading
            try {
                val details = courseRepository.getCourseDetails(courseCode)
                _courseDetailsResult.value = CourseDetailsResult.Success(details)
            } catch (e: Exception) {
                _courseDetailsResult.value = CourseDetailsResult.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
