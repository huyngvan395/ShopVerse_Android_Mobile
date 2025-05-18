package com.example.shopverse.data.api

import com.example.shopverse.data.models.Order
import com.example.shopverse.data.models.request.CreateOrderRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @POST("order/{id}/buy-now/create")
    suspend fun createOrderBuyNow(@Path("id")id:Int,@Body createOrderRequest: CreateOrderRequest):Order
    @POST("order/{id}/cart/create")
    suspend fun createOrderFromCart(@Path("id") id: Int):Order
    @POST("order/add-address")
    suspend fun addAddress():Boolean
    @GET("order/{id}/count")
    suspend fun getOrderCount(@Path("id") id: Int):Int
    @GET("order/{id}")
    suspend fun getAllOrder(@Path("id") id: Int):List<Order>?
}