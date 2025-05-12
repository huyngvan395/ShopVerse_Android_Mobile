package com.example.shopverse.data.api

import com.example.shopverse.data.models.User
import com.example.shopverse.data.models.request.UpdatePasswordRequest
import com.example.shopverse.data.models.request.UpdateUserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: Int): User
    @GET("user/{name}")
    suspend fun getUserList(@Path("name") name: String): List<User>
    @POST("user/{id}/update")
    suspend fun updateUser(@Path("id") id: Int,@Body updateUserRequest: UpdateUserRequest): User
    @POST("user/{id}/update-pass")
    suspend fun updatePassword(@Path("id") id: Int,@Body updatePasswordRequest: UpdatePasswordRequest): Boolean

}

