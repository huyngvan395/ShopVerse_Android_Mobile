package com.example.shopverse.data.repositories

import com.example.shopverse.data.api.UserApi
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.User
import com.example.shopverse.data.models.request.UpdatePasswordRequest
import com.example.shopverse.data.models.request.UpdateUserRequest

class UserRepository(private val userApi: UserApi) {
    suspend fun getUserById(id: Int): User{
        return userApi.getUser(id)
    }
    suspend fun searchUserByName(name: String): List<User>{
        return userApi.getUserList(name)
    }
    suspend fun updateUser(id: Int,updateUserRequest: UpdateUserRequest):User{
        return userApi.updateUser(id,updateUserRequest)
    }
    suspend fun updatePass(id: Int,updatePasswordRequest: UpdatePasswordRequest):Boolean{
        return userApi.updatePassword(id,updatePasswordRequest)
    }
}