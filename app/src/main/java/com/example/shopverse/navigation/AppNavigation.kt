package com.example.shopverse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopverse.MyApplication
import com.example.shopverse.ui.screen.auth.LoginScreen
import com.example.shopverse.ui.screen.auth.RegisterScreen
import com.example.shopverse.ui.screen.home.HomeScreen
import com.example.shopverse.ui.screen.others.NotificationsScreen
import com.example.shopverse.ui.screen.social.PersonalScreen
import com.example.shopverse.viewmodel.auth.LoginViewModel
import com.example.shopverse.viewmodel.auth.RegisterViewModel
import com.example.shopverse.viewmodel.profile.ProfileViewModel
import com.example.shopverse.viewmodel.shop.HomeViewModel
import com.example.shopverse.viewmodel.social.PersonalViewModel
import com.example.shopverse.ui.screen.profile.ProfileScreen
import com.example.shopverse.ui.screen.profile.UpdatePersonalInfoScreen
import com.example.shopverse.ui.screen.shop.CartScreen
import com.example.shopverse.ui.screen.shop.ProductDetailScreen
import com.example.shopverse.ui.screen.social.CommunityScreen
import com.example.shopverse.viewmodel.profile.UpdatePersonalInfoViewModel
import com.example.shopverse.viewmodel.shop.CartViewModel
import com.example.shopverse.viewmodel.shop.ProductDetailViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val appContainer = MyApplication.appContainer
    NavHost(navController= navController,  startDestination = Screen.LoginScreen.route){
        composable(Screen.LoginScreen.route) {
            val loginViewModel: LoginViewModel = viewModel(factory = appContainer.provideLoginViewModelFactory())
            LoginScreen(navController, loginViewModel)
        }
        composable(Screen.RegisterScreen.route) {
            val registerViewModel: RegisterViewModel = viewModel(factory = appContainer.provideRegisterViewModelFactory())
            RegisterScreen(navController, registerViewModel)
        }
        composable(Screen.HomeScreen.route) {
            val homeViewModel: HomeViewModel = viewModel(factory = appContainer.provideHomeViewModelFactory())
            HomeScreen(navController, homeViewModel)
        }
        composable(
            route = Screen.ProductDetailScreen.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){navBackStackEntry ->
            val productId: Int? = navBackStackEntry.arguments?.getInt("id")
            if(productId!= null){
                val productDetailViewModel: ProductDetailViewModel = viewModel(factory = appContainer.provideProductDetailViewModelFactory(productId))
                ProductDetailScreen(productDetailViewModel,navController)
            }
        }
        composable(
            route = Screen.PersonalScreen.route,
            arguments = listOf(navArgument("id"){
                type =NavType.IntType
            })
        ) {navBackStackEntry ->
            val userId: Int? = navBackStackEntry.arguments?.getInt("id")
            if (userId != null) {
                val personalViewModel: PersonalViewModel = viewModel(factory = appContainer.providePersonalViewModelFactory(userId))
                PersonalScreen(navController, personalViewModel)
            }
        }
        composable(
            route = Screen.ProfileScreen.route,
        ) {
            val profileViewModel: ProfileViewModel = viewModel(factory = appContainer.provideProfileViewModelFactory())
            ProfileScreen(navController)
        }
        composable(
            route = Screen.UpdateInformationScreen.route,
        ) {
            val updatePersonalInfoViewModel: UpdatePersonalInfoViewModel = viewModel(factory = appContainer.provideUpdateInfoViewModelFactory())
            UpdatePersonalInfoScreen(navController,updatePersonalInfoViewModel)
        }
        composable(
            route = Screen.NotificationsScreen.route
        ) {
            NotificationsScreen(navController)
        }

        composable(
            route = Screen.CommunityScreen.route
        ) {
            CommunityScreen(navController)
        }

        composable(
            route = Screen.CartScreen.route
        ) {
            val cartViewModel: CartViewModel = viewModel(factory = appContainer.provideCartViewModelFactory())
            CartScreen(cartViewModel,navController)
        }

    }
}