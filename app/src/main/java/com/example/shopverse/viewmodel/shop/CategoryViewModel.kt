package com.example.shopverse.viewmodel.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.repositories.CategoryRepository
import com.example.shopverse.data.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
):ViewModel() {
    private val _listProduct= MutableStateFlow<List<Product>>(emptyList())
    val listProduct = _listProduct.asStateFlow()

    fun loadProductsByCategory(){
        viewModelScope.launch {

        }
    }
}