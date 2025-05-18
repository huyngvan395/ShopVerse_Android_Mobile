package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import com.example.shopverse.navigation.Screen
import com.example.shopverse.ui.components.BottomNavBar
import com.example.shopverse.ui.components.ProductCard
import com.example.shopverse.ui.components.TopBarShop
import com.example.shopverse.viewmodel.shop.SearchResultViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchResultScreen(
    searchResultViewModel: SearchResultViewModel,
    navController:NavController
){
    val products by searchResultViewModel.products.collectAsState()
    val isLoading by searchResultViewModel.isLoading.collectAsState()
    val error by searchResultViewModel.error.collectAsState()
    val scrollState = rememberScrollState()
    var searchText by remember { mutableStateOf("") }
    val cartCount by searchResultViewModel.cartCount.asIntState()
    val currentUser = MyApplication.appContainer.getCurrentUser()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        searchResultViewModel.getCartCount()
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
                            },
                            onToggleFavorite = {
                                scope.launch {
                                    searchResultViewModel.addFavourite(AddToFavouriteRequest(currentUser!!.id,product.id))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}