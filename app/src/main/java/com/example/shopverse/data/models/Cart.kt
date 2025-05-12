package com.example.shopverse.data.models

import com.google.gson.annotations.SerializedName

data class Cart(
    val id: Int,
    @SerializedName("cartItem")
    val cartItemList: List<CartItem>
)
