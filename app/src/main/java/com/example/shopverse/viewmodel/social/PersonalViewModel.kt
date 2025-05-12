package com.example.shopverse.viewmodel.social

import androidx.lifecycle.ViewModel
import com.example.shopverse.data.repositories.UserRepository

class PersonalViewModel(
    private val userId: Int,
    private val userRepository: UserRepository
): ViewModel() {

}