package com.example.shopverse.data.api

import com.example.shopverse.data.models.request.GoogleLoginRequest
import com.example.shopverse.data.models.request.LoginRequest
import com.example.shopverse.data.models.User
import com.example.shopverse.data.models.response.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthApi{
    @Multipart
    @POST("auth/register")
    suspend fun register(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part image: MultipartBody.Part?
    ) : User
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponse
    @POST("auth/login-gg")
    suspend fun ggLogin(@Body googleSignInRequest: GoogleLoginRequest) : User
}

