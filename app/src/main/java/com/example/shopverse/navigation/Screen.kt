package com.example.shopverse.navigation

sealed class Screen (val route: String) {
    data object LoginScreen: Screen("login")
    data object RegisterScreen:  Screen("register")
    data object HomeScreen:   Screen("home")
    data object ProductDetailScreen: Screen("product/{id}")
    data object CommunityScreen: Screen("community")
    data object PersonalScreen: Screen("user/personal/{id}")
    data object ProfileScreen: Screen("user/profile")
    data object UpdateInformationScreen: Screen("user/profile/update-info")
    data object NotificationsScreen: Screen("notifications")
    data object CartScreen: Screen("cart")
}