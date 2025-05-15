package com.example.shopverse

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.data.api.RetrofitClient
import com.example.shopverse.data.models.User
import com.example.shopverse.data.repositories.AuthRepository
import com.example.shopverse.data.repositories.CartRepository
import com.example.shopverse.data.repositories.CategoryRepository
import com.example.shopverse.data.repositories.ProductRepository
import com.example.shopverse.data.repositories.ReviewRepository
import com.example.shopverse.data.repositories.UserRepository
import com.example.shopverse.viewmodel.auth.LoginViewModel
import com.example.shopverse.viewmodel.auth.RegisterViewModel
import com.example.shopverse.viewmodel.profile.ProfileViewModel
import com.example.shopverse.viewmodel.profile.UpdatePersonalInfoViewModel
import com.example.shopverse.viewmodel.shop.CartViewModel
import com.example.shopverse.viewmodel.shop.CategoryViewModel
import com.example.shopverse.viewmodel.shop.CheckoutViewModel
import com.example.shopverse.viewmodel.shop.HomeViewModel
import com.example.shopverse.viewmodel.shop.ProductDetailViewModel
import com.example.shopverse.viewmodel.shop.WishListViewModel
import com.example.shopverse.viewmodel.social.PersonalViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AppContainer(private val context: Context) {
    private val authApi = RetrofitClient.authApi
    private val productApi = RetrofitClient.productApi
    private val cartApi = RetrofitClient.cartApi
    private val userApi = RetrofitClient.userApi
    private val reviewApi = RetrofitClient.reviewApi
    private val categoryApi = RetrofitClient.categoryApi

    private val authRepository = AuthRepository(authApi)
    private val productRepository = ProductRepository(productApi)
    private val userRepository = UserRepository(userApi)
    private val reviewRepository = ReviewRepository(reviewApi)
    private val cartRepository = CartRepository(cartApi)
    private val categoryRepository = CategoryRepository(categoryApi)


    private var currentUser = MutableStateFlow<User?>(null)


    fun provideLoginViewModelFactory(): ViewModelProvider.Factory{
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(authRepository) as T
            }
        }
    }

    fun provideRegisterViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>): T {
                return RegisterViewModel(authRepository) as T
            }
        }
    }

    fun provideHomeViewModelFactory(): ViewModelProvider.Factory{
        return object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(productRepository,cartRepository) as T
            }
        }
    }

    fun provideProductDetailViewModelFactory(id: Int): ViewModelProvider.Factory{
        return object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductDetailViewModel(productRepository,reviewRepository,cartRepository,id) as T
            }
        }
    }

    fun providePersonalViewModelFactory(userId: Int): ViewModelProvider.Factory{
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PersonalViewModel(userId,userRepository) as T
            }
        }
    }

    fun provideProfileViewModelFactory(): ViewModelProvider.Factory{
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfileViewModel( authRepository, productRepository) as T
            }
        }
    }

    fun provideUpdateInfoViewModelFactory(): ViewModelProvider.Factory{
        return object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UpdatePersonalInfoViewModel(userRepository) as T
            }
        }
    }

    fun provideCartViewModelFactory(): ViewModelProvider.Factory{
        return object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CartViewModel(cartRepository,reviewRepository) as T
            }
        }
    }

    fun provideCategoryViewModelFactory(): ViewModelProvider.Factory{
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CategoryViewModel(categoryRepository,productRepository) as T
            }
        }
    }

    fun provideWishListViewModelFactory():ViewModelProvider.Factory{
        return object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WishListViewModel(productRepository) as T
            }
        }
    }

    fun provideCheckoutViewModelFactory():ViewModelProvider.Factory{
        return object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CheckoutViewModel(cartRepository, productRepository) as T
            }
        }
    }

    fun getCurrentUser():User?{
        return currentUser.value
    }

    fun setCurrentUser(user: User){
        this.currentUser.value = user
    }
}