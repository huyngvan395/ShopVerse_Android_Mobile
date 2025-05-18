package com.example.shopverse.viewmodel.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.Address
import com.example.shopverse.data.repositories.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectAddressViewModel(
    private val addressRepository: AddressRepository
):ViewModel() {
    private val _listAddress = MutableStateFlow<List<Address>>(emptyList())
    val listAddress = _listAddress.asStateFlow()
    val user = MyApplication.appContainer.getCurrentUser()

    init {
        loadAddresses()
    }

    private fun loadAddresses(){
        viewModelScope.launch {
            val result = addressRepository.getAllAddresses(user!!.id)
            _listAddress.value = result
        }
    }
}