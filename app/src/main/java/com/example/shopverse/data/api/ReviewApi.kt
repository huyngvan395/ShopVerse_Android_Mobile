package com.example.shopverse.data.api

import android.provider.MediaStore.Images
import com.example.shopverse.data.models.Review
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ReviewApi {
    @GET("/review/{productId}")
    suspend fun getListReviews(): List<Review>
    @Multipart
    @POST("/review/add")
    suspend fun addReview(
        @Part("productId") productId: RequestBody,
        @Part("rating") rating:RequestBody,
        @Part("content") content: RequestBody,
        @Part("userId") userId: RequestBody,
        @Part images: List<MultipartBody.Part>?
    ): Review
}