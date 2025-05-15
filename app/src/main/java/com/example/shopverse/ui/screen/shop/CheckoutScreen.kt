package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.R
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.viewmodel.shop.CheckoutViewModel

@Composable
fun CheckoutScreen(
    navController: NavController,
    checkoutViewModel: CheckoutViewModel,
    mode:String,
    productId: Int?,
    quantity:Int?
){
    if(mode=="buyNow"){

    } else if(mode=="fromCart"){
        val listProduct by checkoutViewModel.listCheckout.collectAsState()
    }
    Scaffold(
        topBar = { CheckoutTopBar(onBackClick = {navController.popBackStack()}) },
        bottomBar = {}
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutTopBar(
    onBackClick:()->Unit={}
){
    TopAppBar(
        title = { Text("Thanh toán") },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun CheckoutBottomBar(
    quantityProduct:Int,
    total:Int,
    checkoutOnclick:()->Unit ={}
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Tổng số tiền ($quantityProduct sản phẩm)"
            )
            Text(
                text = "${total}đ"
            )
        }
        Button(
            onClick = {checkoutOnclick()},
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor
            )
        ) {
            Text(
                text = "Đặt hàng",
                color = Color.White
            )
        }
    }
}