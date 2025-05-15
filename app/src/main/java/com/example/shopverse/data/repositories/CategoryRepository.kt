package com.example.shopverse.data.repositories

import com.example.shopverse.data.api.CategoryApi
import com.example.shopverse.data.models.Category

class CategoryRepository(private val categoryApi: CategoryApi) {
    suspend fun getAllCategories():List<Category>{
        return categoryApi.getAllCategories()
    }
}