package com.example.shopverse.data.repositories

import com.example.shopverse.data.api.ProductApi
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.request.AddToFavouriteRequest

class ProductRepository(private val productApi: ProductApi) {
    suspend fun getAllProducts(id:Int): List<Product>{
        return productApi.getAllProducts(id)
    }
    suspend fun getProductById(id: Int,userId: Int): Product{
        return productApi.getProductById(id,userId)
    }
    suspend fun filterProductByCategory(category: String): List<Product>{
        return productApi.filterProductByCategory(category)
    }
    suspend fun addToFavourite(addToFavouriteRequest: AddToFavouriteRequest):Boolean{
        return productApi.addToFavourite(addToFavouriteRequest)
    }
    suspend fun getProductWishList(userId: Int):List<Product>{
        return productApi.getProductWishList(userId)
    }
}