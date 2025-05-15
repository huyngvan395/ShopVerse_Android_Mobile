package com.example.shopverse.viewmodel.shop

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import com.example.shopverse.data.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WishListViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _listProduct = MutableStateFlow<List<Product>>(emptyList())
    val listProduct = _listProduct.asStateFlow()
    private val currentUser= MyApplication.appContainer.getCurrentUser()

    init {
        loadProductWishList()
    }

    fun loadProductWishList(){
        viewModelScope.launch {
            val result = productRepository.getProductWishList(currentUser!!.id)
            if(result.isNotEmpty()){
                _listProduct.value=result
            }else{
                _listProduct.value = emptyList()
            }
        }
    }

    suspend fun addFavourite(addToFavouriteRequest: AddToFavouriteRequest):Boolean{
        return try {
            productRepository.addToFavourite(addToFavouriteRequest)
        } catch (e:Exception){
            Log.e("WishListViewModel","Lỗi khi cập nhật yêu thích: ${e.message}")
            false
        }
    }
}