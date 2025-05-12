package com.example.shopverse.data.repositories

import com.example.shopverse.data.api.CartApi
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.data.models.request.AddToCartRequest
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import com.example.shopverse.data.models.request.UpdateQuantityRequest
import okhttp3.ResponseBody

class CartRepository(private val cartApi: CartApi) {
    suspend fun addToCart(addToCartRequest: AddToCartRequest):ResponseBody{
        return cartApi.addCart(addToCartRequest)
    }
    suspend fun getCart(id: Int): List<CartItem>{
        return cartApi.getCart(id)
    }
    suspend fun getCartSelected(id: Int): List<CartItem>{
        return cartApi.getCartSelected(id)
    }
    suspend fun countCart(id:Int): Int{
        return cartApi.getCountCart(id)
    }
    suspend fun removeCartItem(id: Int):Boolean{
        return cartApi.removeCartItem(id)
    }
    suspend fun updateCartItem(id: Int,updateQuantityRequest: UpdateQuantityRequest): Boolean{
        return cartApi.updateCartItem(id,updateQuantityRequest)
    }
    suspend fun getQuantitySelected(id: Int):Int{
        return cartApi.getQuantitySelected(id)
    }
    suspend fun updateSelectedCart(id: Int):Boolean{
        return cartApi.updateSelectedCart(id)
    }
}