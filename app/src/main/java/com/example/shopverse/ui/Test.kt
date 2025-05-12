package com.example.shopverse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopverse.R

// Định nghĩa các màu tùy chỉnh
val DeepPurple = Color(0xFF673AB7)
val LightPurple = Color(0xFF9575CD)
val Purple = Color(0xFF9C27B0)
val Pink = Color(0xFFE91E63)
val Green = Color(0xFF4CAF50)
val Gray = Color(0xFF9E9E9E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
            )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { /* Điều hướng đến Home */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Wishlist") },
                    label = { Text("Wishlist") },
                    selected = false,
                    onClick = { /* Điều hướng đến Wishlist */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Email, contentDescription = "Chat") },
                    label = { Text("Chat") },
                    selected = false,
                    onClick = { /* Điều hướng đến Chat */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                    label = { Text("Cart") },
                    selected = false,
                    onClick = { /* Điều hướng đến Cart */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = true,
                    onClick = { /* Đã ở trang Profile */ }
                )
            }
        },

        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DeepPurple, LightPurple)
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Thẻ thông tin người dùng
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar), // Thay bằng tài nguyên ảnh thực tế
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = "John Doe", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(text = "john.doc@example.com", fontSize = 14.sp, color = Gray)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Các ô thống kê
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Tile("Orders", 12, Icons.Default.ShoppingCart, Purple)
                    Tile("Wishlist", 8, Icons.Default.Favorite, Pink)
                    Tile("Shipping", 2, painterResource(R.drawable.local_shipping), Green)
                }
                Spacer(modifier = Modifier.height(24.dp))
                // Phần cài đặt tài khoản
                Text(
                    text = "Account Settings",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                SettingOption("Personal Details", "Update your personal information", Icons.Default.Person, Purple)
                SettingOption("Change Password", "Update your password", Icons.Default.Lock, Purple)
                SettingOption("Notifications", "Manage your notifications", Icons.Default.Notifications, Purple)
                Spacer(modifier = Modifier.height(16.dp))
                // Phần tùy chọn mua sắm
                Text(
                    text = "Shopping Preferences",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                SettingOption("My Orders", "View your order history", painterResource(R.drawable.shopping_bag), Pink)
                SettingOption("Shipping Address", "Manage your delivery addresses", Icons.Default.LocationOn, Pink)
                SettingOption("Payment Methods", "Manage your payment options", painterResource(R.drawable.credit_card), Pink)
            }
        }
    }
}

@Composable
fun Tile(title: String, count: Int, icon: Any, color: Color) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = count.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            when (icon) {
                is ImageVector -> Icon(imageVector = icon, contentDescription = title, tint = Color.White)
                is Painter -> Image(painter = icon, contentDescription = title, modifier = Modifier.size(24.dp))
            }
            Text(text = title, color = Color.White)
        }
    }
}

@Composable
fun SettingOption(title: String, subtitle: String, icon: Any, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Điều hướng đến chi tiết */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = iconColor.copy(alpha = 0.2f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            when (icon) {
                is ImageVector -> Icon(imageVector = icon, contentDescription = title, tint = iconColor)
                is Painter -> Image(
                    painter = icon,
                    contentDescription = title,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = subtitle, fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Go to $title", tint = Color.White)
    }
}

@Composable
fun ProfileScreenTest() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Phần header nền xanh
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(listOf(Color(0xFF5C6BC0), Color(0xFF3949AB))),
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
        )

        // Card avatar đè giữa phần xanh và trắng
        Card(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 140.dp)
                .width(300.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("John Doe", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("john.doe@example.com", color = Color.Gray, fontSize = 14.sp)
            }
        }

        // Nội dung bên dưới
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 350.dp) // Đẩy xuống dưới card
        ) {
            // Info Boxes
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InfoBox(count = "12", label = "Orders", icon = painterResource(R.drawable.shopping_bag))
                InfoBox(count = "8", label = "Wishlist", icon = Icons.Default.Favorite)
                InfoBox(count = "2", label = "Shipping", icon = painterResource(R.drawable.local_shipping))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Account Settings
            Text(
                text = "Account Settings",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                fontSize = 16.sp
            )

            SettingItem("Personal Details", "Update your personal information", Icons.Default.Person)
            SettingItem("Change Password", "Update your password", Icons.Default.Lock)
            SettingItem("Notifications", "Manage your notifications", Icons.Default.Notifications)
        }
    }
}


@Composable
fun InfoBox(count: String, label: String, icon: Any) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (icon) {
            is ImageVector -> Icon(icon, contentDescription = label, tint = Color(0xFF3949AB), modifier = Modifier.size(28.dp))
            is Painter -> Icon(painter = icon, contentDescription = label, tint = Color(0xFF3949AB), modifier = Modifier.size(28.dp))
            else -> Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color.LightGray, shape = CircleShape)
            ) // fallback
        }
        Text(text = count, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun SettingItem(title: String, subtitle: String, icon: Any) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        when (icon) {
            is ImageVector -> Icon(icon, contentDescription = title, tint = Color(0xFF5C6BC0), modifier = Modifier.size(24.dp))
            is Painter -> Icon(painter = icon, contentDescription = title, tint = Color(0xFF5C6BC0), modifier = Modifier.size(24.dp))
            else -> Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.LightGray, shape = CircleShape)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = subtitle, color = Color.Gray, fontSize = 14.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreenTest(){
    ProfileScreenTest()
}
