package com.example.shopverse.viewmodel.profile

import androidx.lifecycle.ViewModel
import com.example.shopverse.data.repositories.AuthRepository
import com.example.shopverse.data.repositories.ProductRepository

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val productRepository: ProductRepository
):ViewModel() {

}