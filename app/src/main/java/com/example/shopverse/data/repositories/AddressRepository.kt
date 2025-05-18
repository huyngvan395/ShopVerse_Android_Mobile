package com.example.shopverse.data.repositories

import com.example.shopverse.data.api.AddressApi
import com.example.shopverse.data.models.Address

class AddressRepository(private val addressApi: AddressApi) {
    suspend fun getAllAddresses(id:Int):List<Address>{
        return addressApi.getAllAddresses(id)
    }
    suspend fun getAddress(id: Int):Address{
        return addressApi.getAddress(id)
    }
}