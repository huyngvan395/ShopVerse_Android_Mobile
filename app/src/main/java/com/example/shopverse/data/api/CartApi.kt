package com.example.shopverse.data.api

import com.example.shopverse.data.models.Cart
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.data.models.request.AddAllSelectedRequest
import com.example.shopverse.data.models.request.AddToCartRequest
import com.example.shopverse.data.models.request.UpdateQuantityRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {
    @GET("cart/{id}")
    suspend fun getCart(@Path("id") id: Int): List<CartItem>
    @GET("cart/{id}/selected")
    suspend fun getCartSelected(@Path("id") id: Int): List<CartItem>
    @POST("cart/add-cart")
    suspend fun addCart(@Body addToCartRequest: AddToCartRequest): ResponseBody
    @GET("cart/{id}/count")
    suspend fun getCountCart(@Path("id") id: Int):Int
    @POST("cart/remove/{id}")
    suspend fun removeCartItem(@Path("id") cartItemId: Int):Boolean
    @POST("cart/update/{id}")
    suspend fun updateCartItem(@Path("id") id: Int, @Body updateQuantityRequest: UpdateQuantityRequest): Boolean
    @GET("cart/get-quantity-selected/{id}")
    suspend fun getQuantitySelected(@Path("id") id: Int): Int
    @POST("cart/update/selected/{id}")
    suspend fun updateSelectedCart(@Path("id") id: Int): Boolean
    @POST("cart/update/all-selected/{id}")
    suspend fun updateAllSelectedCart(@Path("id") id: Int,addAllSelectedRequest: AddAllSelectedRequest): Boolean
}