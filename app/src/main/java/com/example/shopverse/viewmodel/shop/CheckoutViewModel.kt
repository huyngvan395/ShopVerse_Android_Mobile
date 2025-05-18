package com.example.shopverse.viewmodel.shop

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.Address
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.request.CreateOrderRequest
import com.example.shopverse.data.repositories.AddressRepository
import com.example.shopverse.data.repositories.CartRepository
import com.example.shopverse.data.repositories.OrderRepository
import com.example.shopverse.data.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
    private val orderRepository:OrderRepository,
    private val addressRepository: AddressRepository
):ViewModel() {
    private val _listCheckout = MutableStateFlow<List<CartItem>>(emptyList())
    val listCheckout = _listCheckout.asStateFlow()
    private val _address = MutableStateFlow<Address?>(null)
    val address:StateFlow<Address?> = _address.asStateFlow()
    private val _msg = MutableStateFlow<String>("")
    val msg:StateFlow<String> = _msg.asStateFlow()
    val user = MyApplication.appContainer.getCurrentUser()

    init{
        getCartSelected()
        getAddress()
    }

    private fun getCartSelected(){
        viewModelScope.launch {
            val result = cartRepository.getCartSelected(MyApplication.appContainer.getCurrentUser()!!.id)
            _listCheckout.value = result
        }
    }

    suspend fun loadProduct(productId:Int):Product{
        return productRepository.getProductById(productId,user!!.id)
    }

    fun checkoutFromCart(){
        viewModelScope.launch {
            val result = orderRepository.createOrderFromCart(user!!.id)
        }
    }

    fun buyNow(createOrderRequest: CreateOrderRequest){
        viewModelScope.launch {
            val result = orderRepository.createOrderBuyNow(user!!.id,createOrderRequest)
        }
    }

    fun getAddress(){
        viewModelScope.launch {
            val result = addressRepository.getAddress(user!!.id)
            _address.value=result
        }
    }
}