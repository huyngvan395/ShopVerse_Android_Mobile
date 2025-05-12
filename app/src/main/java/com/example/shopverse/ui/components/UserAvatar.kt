package com.example.shopverse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun UserAvatar(
    imageUrl: String?,
    userName: String,
    size: Dp = 40.dp,
    onClick: (() -> Unit)? = null,
    showBorder: Boolean = false,
    borderColor: Color = MaterialTheme.colorScheme.primary
) {
    val modifier = Modifier
        .size(size)
        .clip(CircleShape)
        .let { mod ->
            if (showBorder) {
                mod.border(
                    width = 2.dp,
                    color = borderColor,
                    shape = CircleShape
                )
            } else {
                mod
            }
        }
        .let { mod ->
            if (onClick != null) {
                mod.clickable(onClick = onClick)
            } else {
                mod
            }
        }

    if (!imageUrl.isNullOrEmpty()) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Avatar of $userName",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    } else {
        // Fallback to initials
        val initials = userName
            .split(" ")
            .take(2)
            .joinToString("") { it.take(1) }
            .uppercase()

        Box(
            modifier = modifier.background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = (size.value * 0.4).sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserAvatar(){
    UserAvatar("https://cdn.prod.website-files.com/62d84e447b4f9e7263d31e94/6399a4d27711a5ad2c9bf5cd_ben-sweet-2LowviVHZ-E-unsplash-1.jpeg","Huy")
}
