package com.example.shopverse.data.models.request

data class AddToCartRequest(
    val productId: Int,
    val quantity: Int,
    val userId: Int
)
