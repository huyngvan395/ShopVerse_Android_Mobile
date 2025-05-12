package com.example.shopverse.ui.screen.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.R
import com.example.shopverse.data.models.request.LoginRequest
import com.example.shopverse.navigation.Screen
import com.example.shopverse.ui.components.CustomButton
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.ui.theme.Gray200
import com.example.shopverse.ui.theme.SuccessColor
import com.example.shopverse.viewmodel.auth.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginResult by loginViewModel.loginResult.collectAsState()
    val isLoading by loginViewModel.isLoading.collectAsState()
    val msgError by loginViewModel.msg.collectAsState()

    LaunchedEffect(msgError) {
        if(msgError == "Đăng nhập thành công"){
            navController.navigate("home")
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth().height(250.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 50.sp
                            )
                        ) {
                            append("Shop")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MainColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 50.sp
                            )
                        ) {
                            append("Verse")
                        }
                    }
                )

            }
            Column (
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it},
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it},
                    label = { Text("Mật khẩu") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
                )
                if(msgError!=null){
                    Text(
                        text = msgError!!,
                        fontSize = 15.sp,
                        color = if(msgError == "Đăng nhập thành công") SuccessColor else Color.Red,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Spacer(Modifier.height(10.dp))
                CustomButton(
                    text = "Đăng nhập",
                    onClick = {
                        loginViewModel.login(LoginRequest(email, password))
                    },
                    modifier = Modifier.padding(vertical = 5.dp),
                    isLoading
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Hoặc",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 8.dp) // Khoảng cách hai bên chữ
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Gray200,
                        contentColor = Color.Black
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "Google Logo",
                            modifier = Modifier
                                .size(44.dp)
                                .padding(end = 8.dp),
                            tint = Color.Unspecified
                        )
                        Text("Đăng nhập với Google", fontSize = 20.sp)
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Bạn chưa có tài khoản ? ", color = Color.Black, fontSize = 18.sp)
                    TextButton(
                        onClick = {navController.navigate(Screen.RegisterScreen.route) },

                    ) {
                        Text("Đăng kí", color = MainColor, fontSize = 18.sp)
                    }
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ReviewLogin(){
    val context = android.content.ContextWrapper(null)
    val navController = NavController(context)
//    LoginScreen(navController)
}

