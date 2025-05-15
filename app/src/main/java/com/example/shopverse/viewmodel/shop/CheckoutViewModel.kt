package com.example.shopverse.viewmodel.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.data.repositories.CartRepository
import com.example.shopverse.data.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,

):ViewModel() {
    private val _listCheckout = MutableStateFlow<List<CartItem>>(emptyList())
    val listCheckout = _listCheckout.asStateFlow()

    init{
        getCartSelected()
    }
    private fun getCartSelected(){
        viewModelScope.launch {
            val result = cartRepository.getCartSelected(MyApplication.appContainer.getCurrentUser()!!.id)
            _listCheckout.value = result
        }
    }
}