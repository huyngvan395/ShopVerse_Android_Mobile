package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import com.example.shopverse.ui.components.BottomNavBar
import com.example.shopverse.ui.components.WishListProductCard
import com.example.shopverse.viewmodel.shop.WishListViewModel
import kotlinx.coroutines.launch

@Composable
fun WishListScreen(
    wishListViewModel: WishListViewModel,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val listProduct by wishListViewModel.listProduct.collectAsState()
    val currentUser = MyApplication.appContainer.getCurrentUser()

    Scaffold(
        topBar = {
            WishListTopBar()
        },
        bottomBar = {
            BottomNavBar(navController)
        },
        containerColor = Color.White // Quan trọng để tránh bị "che TopAppBar"
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (listProduct.isEmpty()) {
                item {
                    Text(
                        text = "Bạn chưa thích sản phẩm nào",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray.copy(0.8f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(listProduct) { product ->
                    WishListProductCard(
                        product = product,
                        onClick = {
                            navController.navigate("product/${product.id}")
                        },
                        onToggleFavorite = {
                            scope.launch {
                                wishListViewModel.addFavourite(
                                    AddToFavouriteRequest(currentUser!!.id, product.id)
                                )
                                wishListViewModel.loadProductWishList()
                            }
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListTopBar(){
    TopAppBar(
        title = { Text("Danh sách yêu thích") }
    )
}