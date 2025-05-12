package com.example.shopverse.viewmodel.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.MyApplication
import com.example.shopverse.data.models.request.GoogleLoginRequest
import com.example.shopverse.data.models.request.LoginRequest
import com.example.shopverse.data.models.User
import com.example.shopverse.data.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class LoginViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val _loginResult = MutableStateFlow<User?>(null)
    val loginResult: StateFlow<User?> = _loginResult.asStateFlow()

    private val _loginGgResult = MutableStateFlow<User?>(null)
    val loginGgResult: StateFlow<User?> = _loginGgResult.asStateFlow()

    private val _isLoading =  MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> =  _isLoading.asStateFlow()

    private val _msg = MutableStateFlow<String?>(null)
    val msg: StateFlow<String?> = _msg

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val result = authRepository.login(loginRequest)
                Log.d("LoginVM", result.msg)
                if(result.msg == "Đăng nhập thành công"){
                    _loginResult.value = result.user
                    MyApplication.appContainer.setCurrentUser(result.user!!)
                    _msg.value = result.msg
                    Log.d("LoginVM","Current User: $result")
                } else {
                    _msg.value = result.msg
                }
            }catch (e:HttpException){
                val errorMsg = try {
                    val errorBody = e.response()?.errorBody()?.string()
                    val json = JSONObject(errorBody!!)
                    json.getString("msg")
                } catch (ex: Exception) {
                    "Lỗi đăng nhập"
                }
                _msg.value = errorMsg
                Log.e("LoginVM", "Lỗi: $errorMsg")
            }
            _isLoading.value = false
        }
    }

    fun loginWithGg(googleLoginRequest: GoogleLoginRequest){
        viewModelScope.launch {

        }
    }

}