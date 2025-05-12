package com.example.shopverse.viewmodel.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.Review
import com.example.shopverse.data.models.request.AddToCartRequest
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import com.example.shopverse.data.repositories.CartRepository
import com.example.shopverse.data.repositories.ProductRepository
import com.example.shopverse.data.repositories.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val reviewRepository: ReviewRepository,
    private val cartRepository: CartRepository,
    private val productId: Int
): ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    private val _reviewList = MutableStateFlow<List<Review>>(emptyList())
    val product: StateFlow<Product?> = _product.asStateFlow()
    private val _isLoading =MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _isLoadingAddToCart = MutableStateFlow(false)
    val isLoadingAddToCart: StateFlow<Boolean> = _isLoadingAddToCart.asStateFlow()
    val user = MyApplication.appContainer.getCurrentUser()
    init {
        loadProduct()
    }

    private fun loadProduct(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = productRepository.getProductById(productId,user!!.id)
            _product.value = result
            _isLoading.value = false
        }
    }

    fun addToCart(addToCartRequest: AddToCartRequest){
        viewModelScope.launch {
            _isLoadingAddToCart.value = true
            val result = cartRepository.addToCart(addToCartRequest)
            _isLoadingAddToCart.value = false
        }
    }

    fun addToFavourite(addToFavouriteRequest: AddToFavouriteRequest){
        viewModelScope.launch {
            val result = productRepository.addToFavourite(addToFavouriteRequest)
        }
    }

}