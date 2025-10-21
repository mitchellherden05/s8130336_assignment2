package com.example.s8130336_assignment2.home.network

import com.example.s8130336_assignment2.home.data.LoginRequest
import com.example.s8130336_assignment2.home.data.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }
}
