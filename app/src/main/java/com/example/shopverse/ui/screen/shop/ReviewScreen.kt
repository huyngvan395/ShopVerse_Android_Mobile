package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ReviewScreen(){
    Scaffold(
        topBar = { ReviewTopBar() }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {  }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewTopBar(
    onBackClick: () -> Unit = {}
){
    TopAppBar(
        title = { Text("Đánh giá sản phẩm") },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0x00FFFFFF)
        ),
        modifier = Modifier
            .shadow(
                elevation = 1.dp
            )
    )
}