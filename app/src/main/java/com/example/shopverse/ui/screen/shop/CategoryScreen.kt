package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.shopverse.ui.components.BottomNavBar
import com.example.shopverse.viewmodel.shop.CategoryViewModel

@Composable
fun CategoryScreen(
    categoryViewModel: CategoryViewModel,
    navController: NavController
){
    Scaffold(
       topBar = {} ,
        bottomBar = { BottomNavBar(navController) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth()
        ) {

        }
    }
}