package com.example.shopverse.ui.screen.shop

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shopverse.MyApplication
import com.example.shopverse.R
import com.example.shopverse.data.models.request.AddReviewRequest
import com.example.shopverse.ui.Gray
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.ui.theme.starColor
import com.example.shopverse.viewmodel.shop.WriteReviewViewModel

@Composable
fun WriteReviewScreen(
    navController: NavController,
    writeReviewViewModel: WriteReviewViewModel,
    productId:Int
){
    var rating by remember { mutableStateOf(0) }
    var content by remember { mutableStateOf("") }
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val context = LocalContext.current
    val currentUser = MyApplication.appContainer.getCurrentUser()

    val launcher  = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) {uris ->
        if(uris.size +imageUris.size <=3){
            imageUris =imageUris+uris
        }
    }

    Scaffold(
        topBar = { WriteReviewTopBar(onBackClick = {navController.popBackStack()}) },
        bottomBar = { WriteReviewBottomBar(
            onSubmit = {
                writeReviewViewModel.addReview(
                    context = context,
                    addReviewRequest = AddReviewRequest(
                        productId =productId,
                        rating = rating,
                        content = content,
                        userId = currentUser!!.id,
                        images = imageUris
                    )
                )
            }
        )
        },
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row {
                repeat(5){index->
                    IconButton(
                        onClick = {rating = index+1}
                    ) {
                        Icon(
                            painter =if(index<rating) painterResource(R.drawable.star) else painterResource(R.drawable.star_border),
                            contentDescription = "Star $index",
                            tint = if(index < rating) starColor else Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = content,
                onValueChange = {content = it},
                label = { Text("Viết đánh giá của bạn") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                imageUris.forEach { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    )
                }

                if (imageUris.size < 3) {
                    IconButton(onClick = { launcher.launch("image/*") }) {
                        Icon(painterResource(R.drawable.add_photo), contentDescription = "Thêm ảnh")
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {},
                enabled = rating>0 && content.isNotBlank()
            ) { }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteReviewTopBar(
    onBackClick:()->Unit={}
){
    TopAppBar(
        title = { Text("Viết đánh giá") },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun WriteReviewBottomBar(
    onSubmit:()->Unit={}
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {onSubmit()},
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gửi", color = Color.White, modifier = Modifier.padding(8.dp))
        }
    }
}

