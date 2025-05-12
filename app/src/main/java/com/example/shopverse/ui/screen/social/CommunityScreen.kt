package com.example.shopverse.ui.screen.social

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.shopverse.ui.components.BottomNavBar

@Composable
fun CommunityScreen(
    navController: NavController
){
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {  }
    }
}