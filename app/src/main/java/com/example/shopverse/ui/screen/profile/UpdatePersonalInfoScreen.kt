package com.example.shopverse.ui.screen.profile

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.R
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.viewmodel.profile.UpdatePersonalInfoViewModel
import java.util.Calendar

@Composable
fun UpdatePersonalInfoScreen(
    navController: NavController,
    updatePersonalInfoViewModel: UpdatePersonalInfoViewModel
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { PersonalInfoTopBar(
            onBackClick = {navController.popBackStack()}
        ) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    MainColor,
                                    MainColor.copy(0.9f)
                                )
                            ),
                            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 0.dp)
                            .height(100.dp)
                            .width(100.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color.White.copy(alpha = 0.3f),
                                        Color.White.copy(0.5f)
                                    )
                                ),
                                shape = RoundedCornerShape(
                                    topStart = 50.dp,
                                    bottomStart = 50.dp,
                                    bottomEnd = 50.dp
                                )
                            )
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(top = 50.dp).fillMaxWidth()
                ) {
                    Box(
                        contentAlignment = Alignment.BottomEnd
                    ){
                        Card(
                            elevation = CardDefaults.cardElevation(20.dp),
                            shape = CircleShape
                        ) {
                            Image(
                                painter = painterResource(R.drawable.avatar),
                                contentDescription = null,
                                modifier = Modifier.size(90.dp)
                            )
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .offset(x= (-4).dp, y=(-4).dp)
                                .size(24.dp)
                                .background(Color.White, shape = CircleShape),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.camera),
                                contentDescription = null,
                                tint = MainColor,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier.padding(15.dp).fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(15.dp).fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                "Thông tin cá nhân",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black.copy(alpha = 0.5f)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ItemInfo(painterResource(R.drawable.account_circle), "Tên","text")
                                ItemInfo(Icons.Default.Email, "Email", "text")
                                ItemInfo(Icons.Default.DateRange, "Ngày sinh","date","")
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoTopBar(
    onBackClick: ()-> Unit = {},
    onSaveClick: ()-> Unit = {}
){
    TopAppBar(
        title = {
            Text(
                "Thông tin cá nhân",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = MainColor
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MainColor,
                    modifier = Modifier.size(35.dp),
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveClick) {
                Icon(
                    painter = painterResource(R.drawable.save),
                    contentDescription = null,
                    tint = MainColor,
                    modifier = Modifier.size(35.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        )
    )
}

@Composable
fun ItemInfo(
    icon: Any?,
    name: String,
    type: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    onClick: () -> Unit = {}
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = MainColor.copy(0.1f)
            )
        ) {
            when(icon){
                is ImageVector ->{
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MainColor,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                is Painter -> {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = MainColor,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        if(type == "text"){
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text("Nhập $name") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MainColor.copy(alpha = 0.4f),
                    focusedBorderColor = MainColor,
                    cursorColor = MainColor,
                    focusedLabelColor = MainColor,
                    unfocusedLabelColor = Color.Gray,
                    focusedContainerColor = Color.White.copy(alpha = 0.05f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White.copy(alpha = 0.05f),
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        } else if(type == "date"){
            val context = LocalContext.current
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = remember {
                DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        val selectedDate = "%02d/%02d/%04d".format(selectedDay, selectedMonth + 1, selectedYear)
                        onValueChange(selectedDate)
                    },
                    year, month, day
                )
            }

            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        datePickerDialog.show()
                    },
                label = { Text(name) }
            )
        } else if(type == "select"){
            var expanded by remember { mutableStateOf(false) }
            val options = listOf("Nam", "Nữ", "Khác")

            Box {
                OutlinedTextField(
                    value = value,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().clickable { expanded = true },
                    label = { Text("Chọn giới tính") }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                onValueChange(it)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
