package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopverse.data.models.Order
import com.example.shopverse.ui.Green
import com.example.shopverse.ui.components.OrderProductCard
import com.example.shopverse.viewmodel.shop.OrderViewModel

@Composable
fun OrderScreen(
    navController: NavController,
    orderViewModel: OrderViewModel
){

    val listOrder by orderViewModel.listOrder.collectAsState()
    Scaffold(
        topBar = { OrderTopBar(onBackClick = {navController.popBackStack()}) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn {
                items(listOrder){order->
                    OrderCard(order)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTopBar(
    onBackClick:()->Unit={}
){
    TopAppBar(
        title = { Text("Đơn hàng của tôi") },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun OrderCard(order: Order){
    Column(
        modifier = Modifier.shadow(
            elevation = 5.dp
        ).padding(8.dp)
    ) {
        order.orderItems?.forEach { orderItem ->
            OrderProductCard(orderItem.product,
                orderItem.quantity,
                onClick = {}
            )
        }
        Text(
            text = "Trạng thái: ${order.status}",
            color = Green,
            fontWeight = FontWeight.Bold
        )
    }
}
