package com.example.shopverse.viewmodel.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.data.models.request.UpdateQuantityRequest
import com.example.shopverse.data.repositories.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
): ViewModel() {
    private val _listCartItem = MutableStateFlow<List<CartItem>>(emptyList())
    val listCartItem: StateFlow<List<CartItem>> = _listCartItem.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val  isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _quantitySelected = MutableStateFlow(0)
    val quantitySelected: StateFlow<Int> = _quantitySelected.asStateFlow()
    val user = MyApplication.appContainer.getCurrentUser()

    init {
        loadCartItem()
        getQuantitySelected()
    }

    private fun loadCartItem(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = cartRepository.getCart(user!!.id)
            _listCartItem.value = result
            _isLoading.value =  false
        }
    }

    fun removeCartItem(id: Int){
        viewModelScope.launch {
            val result = cartRepository.removeCartItem(id)
            if(result){
                loadCartItem()
            }
        }
    }

    fun updateCartItem(id: Int, updateQuantityRequest: UpdateQuantityRequest){
        viewModelScope.launch {
            val result = cartRepository.updateCartItem(id,updateQuantityRequest)
        }
    }

    private fun getQuantitySelected(){
        viewModelScope.launch {
            val result = cartRepository.getQuantitySelected(user!!.id)
            _quantitySelected.value = result
        }
    }

    fun updateSelected(id: Int){
        viewModelScope.launch {
            val result = cartRepository.updateSelectedCart(id)
            if(result){
                getQuantitySelected()
            }
        }
    }
}