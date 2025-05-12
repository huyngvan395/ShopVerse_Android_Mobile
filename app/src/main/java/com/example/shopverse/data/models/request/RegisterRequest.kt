package com.example.shopverse.data.models.request

import android.net.Uri

data class RegisterRequest(
    val email: String,
    val password: String,
    val image: Uri? = null
)