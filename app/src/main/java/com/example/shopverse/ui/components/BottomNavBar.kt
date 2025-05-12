package com.example.shopverse.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shopverse.R
import com.example.shopverse.ui.theme.Gray600
import com.example.shopverse.ui.theme.MainColor

sealed class IconType{
    data class Vector(val imageVector: ImageVector): IconType()
    data class PainterIcon(val painter: Painter): IconType()
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: IconType,
    val badgeCount: Int? = null
)

@Composable
fun BottomNavBar(
    navController: NavController,
    isVisibility: Boolean = true,
    modifier: Modifier = Modifier
){
    val items = listOf(
        BottomNavItem("home", "Trang chủ", IconType.Vector(Icons.Default.Home)),
        BottomNavItem("community", "Cộng đồng", IconType.PainterIcon(painterResource(R.drawable.social))),
        BottomNavItem("notifications", "Thông báo", IconType.Vector(Icons.Default.Notifications),3),
        BottomNavItem("user/profile", "Cá nhân", IconType.Vector(Icons.Default.Person))
    )

    AnimatedVisibility(
        visible = isVisibility,
        enter = slideInVertically(initialOffsetY = { it })
    ) {
        NavigationBar (
            modifier = modifier.padding(0.dp)
        ) {
            val navBackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackEntry?.destination

            items.forEach{ item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                val selectedColor = MainColor
                val unselectedColor = Gray600
                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount != null && item.badgeCount >0){
                                    Badge(
                                        modifier = Modifier
                                            .wrapContentSize(align = Alignment.Center)
                                    ){
                                        Text(
                                            text = item.badgeCount.toString(),
                                            fontSize = 15.sp,
                                            modifier = Modifier.padding(2.dp)
                                        )
                                    }
                                }
                            }
                        ) {
                            if (item.icon is IconType.Vector) {
                                Icon(
                                    imageVector = item.icon.imageVector,
                                    contentDescription = item.title,
                                    tint = if(selected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(38.dp)
                                )
                            } else if (item.icon is IconType.PainterIcon) {
                                Icon(
                                    painter = item.icon.painter,
                                    contentDescription = item.title,
                                    tint = if(selected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(38.dp)
                                )
                            }
                        }
                    },
                    onClick = {
                        navController.navigate(route = item.route) {
//                            popUpTo(navController.graph.findStartDestination()) {
//                                saveState = true
//                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selected = selected,
                    modifier = Modifier.padding(0.dp).wrapContentHeight(align = Alignment.CenterVertically),
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = selectedColor.copy(0.1f),
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor
                    )
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewBottomNavBar(){
//    val context = android.content.ContextWrapper(null)
//    val navController = NavController(context)
//
//    BottomNavBar(
//        navController
//    )
//}