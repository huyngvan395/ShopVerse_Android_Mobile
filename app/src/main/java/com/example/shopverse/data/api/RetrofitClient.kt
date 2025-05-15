package com.example.shopverse.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL="http://10.0.2.2:3333/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi: AuthApi by  lazy {
        retrofit.create(AuthApi::class.java)
    }
    val userApi: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
    val productApi: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }
    val cartApi: CartApi by lazy {
        retrofit.create(CartApi::class.java)
    }
    val reviewApi: ReviewApi by lazy {
        retrofit.create(ReviewApi::class.java)
    }
    val categoryApi: CategoryApi by lazy {
        retrofit.create(CategoryApi::class.java)
    }
}
