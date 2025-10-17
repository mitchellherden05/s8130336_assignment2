package com.example.s8130336_assignment2.home.network

import com.google.firebase.appdistribution.gradle.ApiService
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestfulApiDevRetrofitClient {

    private val BASE_URL_DASHBOARD = "https://nit3213api.onrender.com/dashboard/"
    private val BASE_URL_AUTH = "https://nit3213api.onrender.com/footscray/auth"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY

    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_DASHBOARD)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    val apiService: ApiService =
        retrofit.create(ApiService::class.java)
}