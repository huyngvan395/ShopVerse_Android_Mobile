package com.example.shopverse.data.models

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: BigDecimal,
    val category: Category,
    var favourite: Favourite?=null,
    val stock: Int,
    val rating: BigDecimal,
    val reviewCount:Int?=0
)
