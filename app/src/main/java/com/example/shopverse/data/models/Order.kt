package com.example.shopverse.data.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Order(
    val id: Int,
    val userId:Int,
    val totalAmount:BigDecimal,
    val recipientName:String,
    val recipientPhone:String,
    val shippingAddress:String,
    val status:String,
    @SerializedName("orderItem")
    val orderItems:List<OrderItem>?=null
)
