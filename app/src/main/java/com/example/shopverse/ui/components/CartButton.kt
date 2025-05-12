package com.example.shopverse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CartButton(
    cartCount: Int,
    onClick: () -> Unit = {}
){
    BadgedBox(
        badge = {
            if (cartCount >0){
                Badge(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                ){
                    Text(
                        text = cartCount.toString(),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Giỏ hàng",
                tint = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}