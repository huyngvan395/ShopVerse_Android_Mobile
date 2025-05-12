package com.example.shopverse.data.models

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: BigDecimal,
    val category: Category,
    val isFavourite: Boolean,
    val stock: Int,
    val rating: BigDecimal
)
