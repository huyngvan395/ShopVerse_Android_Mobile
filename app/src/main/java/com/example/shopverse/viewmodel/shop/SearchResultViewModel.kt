package com.example.shopverse.viewmodel.shop

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import com.example.shopverse.data.repositories.CartRepository
import com.example.shopverse.data.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    search:String
):ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()
    private val  _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    private val _cartCount = mutableIntStateOf(0)
    val cartCount: State<Int> = _cartCount

    init{
        loadProducts(search)
    }

    private fun loadProducts(search:String){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _products.value = productRepository.searchProducts(MyApplication.appContainer.getCurrentUser()!!.id, search)
            } catch (e: Exception){
                _error.value = e.message ?: "Lỗi không tải sản phẩm"
                Log.e("ErrorHome", e.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCartCount(){
        viewModelScope.launch {
            try {
                val count = cartRepository.countCart(MyApplication.appContainer.getCurrentUser()!!.id)
                _cartCount.intValue = count
            } catch (e: Exception){
                Log.e("HomeViewModel","Lỗi khi lấy cartCount: ${e.message}")
            }
        }
    }

    suspend fun addFavourite(addToFavouriteRequest: AddToFavouriteRequest):Boolean{
        return try {
            productRepository.addToFavourite(addToFavouriteRequest)
        } catch (e:Exception){
            Log.e("HomeViewModel","Lỗi khi cập nhật yêu thích: ${e.message}")
            false
        }
    }
}