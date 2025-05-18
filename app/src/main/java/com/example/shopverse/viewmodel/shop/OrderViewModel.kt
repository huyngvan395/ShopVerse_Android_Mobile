package com.example.shopverse.viewmodel.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.Order
import com.example.shopverse.data.repositories.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    private val orderRepository: OrderRepository
):ViewModel() {
    private val _listOrder = MutableStateFlow<List<Order>>(emptyList())
    val listOrder:StateFlow<List<Order>> = _listOrder.asStateFlow()
    val user = MyApplication.appContainer.getCurrentUser()

    init {
        getAllOrder()
    }

    private fun getAllOrder(){
        viewModelScope.launch {
            val result = orderRepository.getAllOrder(user!!.id)
            if(result!=null){
                _listOrder.value= result
            }else{
                _listOrder.value= emptyList()
            }
        }
    }
}