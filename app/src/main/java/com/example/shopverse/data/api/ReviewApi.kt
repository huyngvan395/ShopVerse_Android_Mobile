package com.example.shopverse.data.api

import com.example.shopverse.data.models.Review
import retrofit2.http.GET

interface ReviewApi {
    @GET("/review/{productId}")
    suspend fun getListReviews(): List<Review>
}