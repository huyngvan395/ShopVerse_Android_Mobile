package com.example.shopverse.data.models.response

import com.example.shopverse.data.models.User

data class LoginResponse(
    val user: User?=null,
    val token: String?= null,
    val msg: String
)