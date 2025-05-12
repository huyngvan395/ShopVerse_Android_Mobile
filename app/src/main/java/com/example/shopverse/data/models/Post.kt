package com.example.shopverse.data.models

data class Post(
    val id: Int,
    val title: String,
    val content: String,
    val listComment: List<Comment>
)
