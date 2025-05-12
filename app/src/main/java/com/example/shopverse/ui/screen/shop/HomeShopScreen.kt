package com.example.shopverse.ui.screen.shop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.shopverse.data.models.Category
import com.example.shopverse.data.models.Product

//@Composable
//fun HomeShopScreen(
//    navController: NavController,
//    modifier: Modifier = Modifier
//){
//    val categories = remember {
//        listOf(
//            Category(1, "Fashion"),
//            Category(2,"Electronics"),
//            Category(3,"Home"),
//            Category(4,"Beauty"),
//            Category(5,"Sports"),
//            Category(6,"Books")
//        )
//    }
//
//    val featuredProducts = remember {
//        listOf(
//            Product(1, "Wireless Earbuds","Description is here", "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-bluetooth-xiaomi-redmi-buds-6-active-spa.png",79.99, category = Category(),
//            Product(2, "Smart Watch","Description is here","https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/a/garmin_4.png",79.99),
//            Product(3, "Wireless Earbuds","Description is here", "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-bluetooth-xiaomi-redmi-buds-6-active-spa.png",79.99),
//            Product(4, "Smart Watch","Description is here","https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/a/garmin_4.png",79.99),
//            )
//    }
//
//    val newArrivals = remember {
//        listOf(
//            listOf(
//                Product(5, "Wireless Earbuds 2","Description is here", "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-bluetooth-xiaomi-redmi-buds-6-active-spa.png",79.99),
//                Product(6, "Smart Watch 2","Description is here","https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/a/garmin_4.png",79.99),
//                Product(7, "Wireless Earbuds 2","Description is here", "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-bluetooth-xiaomi-redmi-buds-6-active-spa.png",79.99),
//                Product(8, "Smart Watch 2","Description is here","https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/a/garmin_4.png",79.99),
//            )
//        )
//    }
//
//    val selectedCategoryId by remember { mutableStateOf<String?>(null) }
//
//
//}