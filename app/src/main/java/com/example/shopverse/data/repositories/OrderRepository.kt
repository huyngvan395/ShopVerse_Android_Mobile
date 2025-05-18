package com.example.shopverse.data.repositories

import com.example.shopverse.data.api.OrderApi
import com.example.shopverse.data.models.Order
import com.example.shopverse.data.models.request.CreateOrderRequest

class OrderRepository(private val orderApi: OrderApi){
    suspend fun createOrderBuyNow(id:Int,createOrderRequest: CreateOrderRequest):Order{
        return orderApi.createOrderBuyNow(id,createOrderRequest)
    }
    suspend fun createOrderFromCart(id:Int):Order{
        return orderApi.createOrderFromCart(id)
    }
    suspend fun getOrderCount(id:Int):Int{
        return orderApi.getOrderCount(id)
    }
    suspend fun getAllOrder(id: Int):List<Order>?{
        return orderApi.getAllOrder(id)
    }
}
