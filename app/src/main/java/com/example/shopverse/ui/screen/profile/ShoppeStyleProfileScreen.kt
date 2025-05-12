//// ProfileScreen.kt
//
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ExitToApp
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.shopverse.R
//import com.example.shopverse.ui.screen.profile.IconData
//
//@Composable
//fun ShopeeStyleProfileScreen(
//    userName: String,
//    level: String,
//    avatar: Painter,
//    onVoucherClick: () -> Unit,
//    onTopUpClick: () -> Unit,
//    onLogout: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF7F7F7))
//    ) {
//        // Header
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            shape = RoundedCornerShape(16.dp),
//            elevation = CardDefaults.cardElevation(4.dp)
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Image(
//                    painter = avatar,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(72.dp)
//                        .clip(CircleShape)
//                        .background(Color.Gray)
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Column(modifier = Modifier.weight(1f)) {
//                    Text(userName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                    Text("Hạng: $level", fontSize = 14.sp, color = Color.Gray)
//                }
//                Icon(Icons.Default.Edit, contentDescription = null)
//            }
//        }
//
//        // Action Row
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        ) {
//            Box(modifier = Modifier.weight(1f)) {
//                ActionCard("Voucher của tôi", IconData.Painter(painterResource(R.drawable.card_giftcard)), onVoucherClick)
//            }
//            Spacer(modifier = Modifier.width(12.dp))
//            Box(modifier = Modifier.weight(1f)) {
//                ActionCard("Nạp tiền", IconData.Painter(painterResource(R.drawable.wallet)), onTopUpClick)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Đơn hàng
//        SectionTitle("Đơn mua")
//        LazyRow(
//            contentPadding = PaddingValues(horizontal = 16.dp),
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(5) {
//                OrderStatusCard(label = "Chờ xác nhận", iconData = IconData.Painter(painterResource(R.drawable.schedule)))
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Tiện ích
//        SectionTitle("Tiện ích của tôi")
//        LazyColumn(
//            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            item { SettingItem("Đánh giá của tôi", IconData.Vector(Icons.Default.Star)) }
//            item { SettingItem("Ví điện tử", IconData.Painter(painterResource(R.drawable.wallet))) }
//            item { SettingItem("Hỗ trợ khách hàng", IconData.Painter(painterResource(R.drawable.ic_support))) }
//            item { SettingItem("Cài đặt", IconData.Vector(Icons.Default.Settings)) }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = onLogout,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null, tint = Color.White)
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("Đăng xuất", color = Color.White)
//        }
//    }
//}
//
//@Composable
//fun ActionCard(title: String, iconData: IconData, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .height(80.dp)
//            .fillMaxWidth()
//            .clickable { onClick() },
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(12.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            when (iconData) {
//                is IconData.Vector -> Icon(iconData.icon, contentDescription = null)
//                is IconData.Painter -> Icon(painter = iconData.painter, contentDescription = null)
//                else -> {}
//            }
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(title, fontSize = 14.sp)
//        }
//    }
//}
//
//@Composable
//fun OrderStatusCard(label: String, iconData: IconData) {
//    Card(
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(2.dp),
//        modifier = Modifier.size(width = 100.dp, height = 100.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(12.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            when (iconData) {
//                is IconData.Vector -> Icon(iconData.icon, contentDescription = null)
//                is IconData.Painter -> Icon(painter = iconData.painter, contentDescription = null)
//                else -> {}
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(label, fontSize = 14.sp)
//        }
//    }
//}
//
//@Composable
//fun SettingItem(title: String, iconData: IconData) {
//    Card(
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(2.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp)
//            .clickable { }
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        ) {
//            when (iconData) {
//                is IconData.Vector -> Icon(iconData.icon, contentDescription = null)
//                is IconData.Painter -> Icon(painter = iconData.painter, contentDescription = null)
//                else -> {}
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(title, fontSize = 16.sp, modifier = Modifier.weight(1f))
//            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
//        }
//    }
//}
//
//@Composable
//fun SectionTitle(title: String) {
//    Text(
//        text = title,
//        fontSize = 18.sp,
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ShopeeStyleProfileScreenPreview() {
//    ShopeeStyleProfileScreen(
//        userName = "Huy Trần",
//        level = "Vàng",
//        avatar = painterResource(id = R.drawable.avatar),
//        onVoucherClick = {},
//        onTopUpClick = {},
//        onLogout = {}
//    )
//}
