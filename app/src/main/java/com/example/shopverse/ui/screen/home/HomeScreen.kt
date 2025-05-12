package com.example.shopverse.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.shopverse.data.api.ProductApi
import com.example.shopverse.data.api.RetrofitClient
import com.example.shopverse.data.repositories.ProductRepository
import com.example.shopverse.navigation.Screen
import com.example.shopverse.ui.components.BottomNavBar
import com.example.shopverse.ui.components.ProductCard
import com.example.shopverse.ui.components.TopBarShop
import com.example.shopverse.viewmodel.shop.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
){
    val products by homeViewModel.products.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()
    val error by homeViewModel.error.collectAsState()
    val scrollState = rememberScrollState()
    var searchText by remember { mutableStateOf("") }
    val cartCount by homeViewModel.cartCount.asIntState()

    LaunchedEffect(Unit) {
        homeViewModel.getCartCount()
    }

    Scaffold(
        topBar = { TopBarShop(
            cartCount = cartCount,
            searchText = searchText,
            onSearchChange = {searchText = it},
            onCartClick = {navController.navigate(Screen.CartScreen.route)}
        ) },
        bottomBar = { BottomNavBar(navController) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (isLoading) {
                Text("Đang tải...")
            } else if (error != null) {
                Text("Error: $error")
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            onClick = {
                                navController.navigate("product/${product.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewHomeScreen(){
//    val context = android.content.ContextWrapper(null)
//    val navController = NavController(context)
//    val viewModel: HomeViewModel = HomeViewModel(ProductRepository(RetrofitClient.productApi))
//
//    HomeScreen(navController, viewModel)
//}