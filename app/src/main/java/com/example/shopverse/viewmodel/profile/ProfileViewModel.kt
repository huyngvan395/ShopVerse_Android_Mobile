package com.example.shopverse.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.repositories.AuthRepository
import com.example.shopverse.data.repositories.OrderRepository
import com.example.shopverse.data.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
):ViewModel() {
    private val _wishListCount = MutableStateFlow<Int>(0)
    val wishListCount:StateFlow<Int> = _wishListCount.asStateFlow()
    private val _orderCount = MutableStateFlow<Int>(0)
    val orderCount:StateFlow<Int> = _orderCount.asStateFlow()
    val user = MyApplication.appContainer.getCurrentUser()

    init {
        getWishListCount()
        getOrderCount()
    }

    private fun getWishListCount(){
        viewModelScope.launch {
            val result = productRepository.getWishListCount(user!!.id)
            _wishListCount.value = result
        }
    }

    private fun getOrderCount(){
        viewModelScope.launch {
            val result = orderRepository.getOrderCount(user!!.id)
            _orderCount.value=result
        }
    }
}