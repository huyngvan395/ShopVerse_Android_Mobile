package com.example.shopverse.data.api

import com.example.shopverse.data.models.Category
import retrofit2.http.GET

interface CategoryApi {
    @GET("category/categories")
    suspend fun getAllCategories():List<Category>
}