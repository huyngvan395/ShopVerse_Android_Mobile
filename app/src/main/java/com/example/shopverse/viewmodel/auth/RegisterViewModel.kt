package com.example.shopverse.viewmodel.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.models.request.RegisterRequest
import com.example.shopverse.data.models.User
import com.example.shopverse.data.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream

class RegisterViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val _registerResult = MutableStateFlow<User?>(null)
    val registerResult: StateFlow<User?> = _registerResult.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    fun register(context: Context, registerRequest: RegisterRequest){
        viewModelScope.launch {
            _isLoading.value = true
            val email: RequestBody = registerRequest.email.toRequestBody("text/plain".toMediaType())
            val password: RequestBody = registerRequest.password.toRequestBody("text/plain".toMediaType())
            val image: MultipartBody.Part? = registerRequest.image?.let {
                uri ->
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                val bytes = inputStream?.readBytes()
                val requestBody = bytes?.toRequestBody("image/*".toMediaTypeOrNull())

                requestBody?.let {
                    val fileName = registerRequest.email.substringBefore("@")
                    val mimeType = context.contentResolver.getType(uri)
                    val extension = when (mimeType) {
                        "image/png" -> "png"
                        "image/jpeg" -> "jpg"
                        else -> "jpg"
                    }
                    val file = "$fileName.$extension"
                    MultipartBody.Part.createFormData("image", file, requestBody)
                }
            }
            val result = authRepository.register(email, password, image)
            _registerResult.value = result;
            _isLoading.value = false
        }
    }
}