package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.data.models.request.UpdateQuantityRequest
import com.example.shopverse.ui.components.CartProductCard
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.viewmodel.shop.CartViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    navController: NavController
){
    val listCartItem by cartViewModel.listCartItem.collectAsState()
    val quantitySelected by cartViewModel.quantitySelected.collectAsState()
    Scaffold(
        topBar = { CartTopBar(
            onBackClick = {navController.popBackStack()}
        ) },
        bottomBar = { CartBottomBar(quantitySelected = quantitySelected) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if(listCartItem.isEmpty()){
                Text(
                    "Giỏ hàng của bạn chưa có sản phẩm nào",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray.copy(0.8f),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn() {
                items(listCartItem) { cartItem ->
                    CartProductCard(
                        cartItem = cartItem,
                        rating = 5.0,
                        reviews = "14",
                        onClick = {
                            navController.navigate("product/${cartItem.product.id}")
                        },
                        onRemove = { cartViewModel.removeCartItem(cartItem.id) },
                        onQuantityChange = { newQuantity->
                            cartViewModel.updateCartItem(cartItem.id, UpdateQuantityRequest(newQuantity))
                        },
                        isSelected = cartItem.selected
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(
    onBackClick: () -> Unit ={}
){
    TopAppBar(
        title = { Text("Giỏ hàng") },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun CartBottomBar(
    onBuy: () -> Unit = {},
    quantitySelected: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(50.dp)
    ) {
        Button(
            onClick = {onBuy()},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor
            )
        ) {
            Text(
                text = "Mua hàng(${quantitySelected})",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}