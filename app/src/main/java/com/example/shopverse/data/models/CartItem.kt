package com.example.shopverse.data.models

import com.google.gson.annotations.SerializedName

data class CartItem(
    val id: Int,
    val product: Product,
    val quantity: Int,
    @SerializedName("isSelected")
    val selected: Boolean
)
