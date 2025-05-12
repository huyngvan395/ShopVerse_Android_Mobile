package com.example.shopverse.data.models

data class Order(
    val id: Int,
    val orderItemList: List<OrderItem>
)
