package com.example.s8130336_assignment2.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8130336_assignment2.home.data.LoginRequest
import com.example.s8130336_assignment2.home.data.LoginResponse
import com.example.s8130336_assignment2.home.network.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginResult {
    data class Success(val response: LoginResponse) : LoginResult()
    data class Error(val message: String) : LoginResult()
    object Loading : LoginResult()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun performLogin(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = LoginResult.Loading
            try {
                val loginRequest = LoginRequest(username, password)
                val response = loginRepository.login(loginRequest)

                // Check if the keypass is present in the response.
                if (response.keypass != null) {
                    // If keypass exists, login is successful.
                    _loginResult.value = LoginResult.Success(response)
                } else {
                    // If keypass is null, it means login failed (e.g., invalid credentials).
                    _loginResult.value = LoginResult.Error("Invalid username or password")
                }

            } catch (e: Exception) {
                _loginResult.value = LoginResult.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
