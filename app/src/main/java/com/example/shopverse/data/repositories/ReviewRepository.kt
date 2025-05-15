package com.example.shopverse.data.repositories

import android.content.ContentResolver
import android.net.Uri
import com.example.shopverse.data.api.ReviewApi
import com.example.shopverse.data.models.Review
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ReviewRepository(private val reviewApi: ReviewApi) {
    suspend fun getAllReview():List<Review>{
        return reviewApi.getListReviews()
    }
    suspend fun addReview(
        productId: RequestBody,
        rating: RequestBody,
        content: RequestBody,
        userId: RequestBody,
        imageUris: List<MultipartBody.Part>
    ):Review{
        return reviewApi.addReview(productId,rating,content,userId,imageUris)
    }
}