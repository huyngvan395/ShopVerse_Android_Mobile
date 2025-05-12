package com.example.shopverse.ui.screen.others

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shopverse.ui.components.BottomNavBar
import com.example.shopverse.ui.theme.MainColor

@Composable
fun NotificationsScreen(
    navController: NavController
){
    Scaffold(
        topBar = { TopBarNotifications() },
        bottomBar = {BottomNavBar(navController)}
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun TopBarNotifications(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 30.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(MainColor, MainColor.copy(0.9f))),
                shape = RoundedCornerShape(bottomStart =50.dp, bottomEnd = 50.dp)
            )
    ){
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 0.dp)
                .height(20.dp)
                .width(20.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color.White.copy(alpha = 0.3f), Color.White.copy(0.5f))),
                    shape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp, bottomEnd = 50.dp)
                )
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = "Thông báo",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

}

@Composable
fun NotificationItem(){

}
//@Composable
//fun ProductCard(
//    productName: String = "Elegant Robot",
//    productImageUrl: String = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D"
//) {
//    Surface(
//        shape = RoundedCornerShape(16.dp),
//        tonalElevation = 6.dp,
//        shadowElevation = 8.dp,
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//            .height(250.dp)
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            // Background image
//            AsyncImage(
//                model = productImageUrl,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//
//            // Gradient overlay
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        Brush.verticalGradient(
//                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
//                            startY = 100f
//                        )
//                    )
//            )
//
//            // Product name
//            Text(
//                text = productName,
//                color = Color.White,
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(16.dp)
//            )
//        }
//    }
//}

