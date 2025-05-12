package com.example.shopverse.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.MyApplication
import com.example.shopverse.R
import com.example.shopverse.data.repositories.AuthRepository
import com.example.shopverse.navigation.Screen
import com.example.shopverse.ui.components.BottomNavBar
import com.example.shopverse.ui.theme.MainColor

@Composable
fun ProfileScreen(
    navController: NavController
){
    val currentUser = MyApplication.appContainer.getCurrentUser()
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) {paddingValues->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(MainColor, MainColor.copy(0.9f))),
                            shape = RoundedCornerShape(bottomStart =50.dp, bottomEnd = 50.dp)
                        )
                ){
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 0.dp)
                            .height(100.dp)
                            .width(100.dp)
                            .background(
                                brush = Brush.horizontalGradient(listOf(Color.White.copy(alpha = 0.3f), Color.White.copy(0.5f))),
                                shape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp, bottomEnd = 50.dp)
                            )
                    )
                }

                Card(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .width(250.dp)
                        .padding(top = 100.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Card(
                            shape = CircleShape,
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.avatar),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = if (currentUser?.name?.isNotEmpty() == true) currentUser.name else "Chưa cập nhật tên",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        currentUser?.email?.let {
                            Text(
                                text = it,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 260.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        UltilityItem("Đơn hàng", painterResource(R.drawable.shopping_bag),10)
                        Spacer(modifier = Modifier.width(20.dp))
                        UltilityItem("Yêu thích", Icons.Default.FavoriteBorder, 5)
                        Spacer(modifier = Modifier.width(20.dp))
                        UltilityItem("Đang giao", painterResource(R.drawable.local_shipping), 8)
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    //Tài khoản
                    Text(
                        "Tài khoản",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top=5.dp, start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    SettingItem("Thông tin cá nhân","Cập nhật thông tin cá nhân của bạn", Icons.Default.AccountCircle, onClick = {navController.navigate(Screen.UpdateInformationScreen.route)})
                    SettingItem("Thay đổi mật khẩu","Cập nhật mật khẩu của bạn", Icons.Default.Lock)
                    SettingItem("Thông báo", "Quản lí thông báo", Icons.Default.Notifications)

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Mua sắm",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top=5.dp, start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    SettingItem("Đơn hàng của tôi", "Xem lịch sử mua hàng của bạn", painterResource(R.drawable.shopping_bag))
                    SettingItem("Địa chỉ giao hàng","Quản lí địa chỉ giao hàng của bạn", painterResource(R.drawable.local_shipping))
                    SettingItem("Phương thức thanh toán", "Quản lí lựa chọn thanh toán của bạn", painterResource(R.drawable.wallet))

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Thêm",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top=5.dp, start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    SettingItem("Cài đặt", "Tùy chọn và cài đặt ứng dụng", Icons.Default.Settings)
                    SettingItem("Giúp đỡ và hỗ trợ","Cần giúp đỡ và tương tác với chúng tôi", painterResource(R.drawable.ic_support))
                    SettingItem("Đăng xuất", "Đăng xuất tài khoản của bạn", Icons.AutoMirrored.Filled.ExitToApp)
                }
            }
        }
    }

}

@Composable
fun UltilityItem(
    name: String,
    icon: Any,
    quantity: Int,
    onClick: () -> Unit = {}
){
    Card(
        modifier = Modifier
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 22.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MainColor.copy(alpha = 0.1f)
                )
            ) {
                when(icon){
                    is ImageVector -> {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.padding(15.dp),
                            tint = MainColor
                        )
                    }
                    is Painter ->{
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier.padding(15.dp),
                            tint = MainColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = quantity.toString(),
                color = MainColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun SettingItem(
    name: String,
    des: String,
    icon: Any,
    onClick: () -> Unit = {}
){
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MainColor.copy(alpha = 0.1f)
                )
            ) {
                when(icon){
                    is ImageVector -> {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.padding(15.dp),
                            tint = MainColor
                        )
                    }
                    is Painter ->{
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier.padding(15.dp),
                            tint = MainColor
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = des,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray.copy(alpha = 0.9f)
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MainColor,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

