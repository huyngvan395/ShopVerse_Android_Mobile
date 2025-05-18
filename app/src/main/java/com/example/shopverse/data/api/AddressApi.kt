package com.example.shopverse.data.api

import com.example.shopverse.data.models.Address
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressApi {
    @GET("address/addresses/{id}")
    suspend fun getAllAddresses(@Path("id") id:Int):List<Address>
    @GET("address/{id}/default")
    suspend fun getAddress(@Path("id") id:Int):Address
}