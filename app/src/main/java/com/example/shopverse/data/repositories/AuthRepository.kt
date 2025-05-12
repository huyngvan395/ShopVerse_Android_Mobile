package com.example.shopverse.data.repositories

import com.example.shopverse.data.api.AuthApi
import com.example.shopverse.data.models.request.GoogleLoginRequest
import com.example.shopverse.data.models.request.LoginRequest
import com.example.shopverse.data.models.User
import com.example.shopverse.data.models.response.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthRepository(private val authApi: AuthApi) {
    suspend fun googleLogin(googleLoginRequest: GoogleLoginRequest): User{
        return authApi.ggLogin(googleLoginRequest)
    }
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return authApi.login(loginRequest)
    }
    suspend fun register(
        email: RequestBody,
        password: RequestBody,
        image: MultipartBody.Part?
    ): User{
        return authApi.register(email, password, image)
    }
    suspend fun getCurrentUser(){

    }
}