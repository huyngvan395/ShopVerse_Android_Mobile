package com.example.shopverse.data.api

import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {
    @GET("product/products/{id}")
    suspend fun getAllProducts(@Path("id") id: Int): List<Product>
    @GET("product/{id}/{userId}")
    suspend fun getProductById(@Path("id") id: Int, @Path("userId") userId: Int): Product
    @GET("product/{category}")
    suspend fun filterProductByCategory(@Path("category") category: String): List<Product>
    @GET("product/products/{id}/search/{search}")
    suspend fun searchProducts(@Path("id") id: Int,@Path("search") search: String):List<Product>
    @POST("product/add-favourite")
    suspend fun addToFavourite(@Body addToFavouriteRequest: AddToFavouriteRequest): Boolean
    @GET("product/products/wishlist/{id}")
    suspend fun getProductWishList(@Path("id") id: Int):List<Product>
    @GET("product/products/wishlist/{id}/count")
    suspend fun getWishListCount(@Path("id") id:Int):Int
}