package com.example.shopverse.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullName")
    val name: String? = null,
    @SerializedName("avatarUrl")
    val imageURL: String? = null,
    val phone: String
//    val posts: List<Post>? = null,
//    val reviews: List<Review>? = null,
//    val orders: List<Order>? = null,
//    val followers: List<User>? = null,
//    val following: List<User>? = null
)
