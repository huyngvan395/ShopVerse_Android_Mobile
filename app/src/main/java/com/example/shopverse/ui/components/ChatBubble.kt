package com.example.shopverse.ui.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopverse.R
import com.example.shopverse.data.models.ChatMessage
import com.example.shopverse.data.models.MessageType
import com.example.shopverse.data.models.User
import com.example.shopverse.ui.theme.Gray200
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.ui.theme.fileColor
import java.util.Date
import java.util.Locale

@Composable
fun ChatBubble(
    message: ChatMessage,
    imageUser: String,
    isUserMessage: Boolean,
    modifier: Modifier = Modifier,
    onImageClick: (String) -> Unit = {},
    onFileClick: (String) -> Unit = {}
){
    val bubbleShape = if (isUserMessage){
        RoundedCornerShape(16.dp, 4.dp, 16.dp, 16.dp)
    } else {
        RoundedCornerShape(4.dp, 16.dp, 16.dp, 16.dp)
    }

    val bubbleColor = if (isUserMessage){
        MainColor
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val bubbleFileColor = fileColor

    val textColor = if (isUserMessage){
        Gray200
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    val timeDateFormatter = SimpleDateFormat("h:mm a", Locale.getDefault())
    val timeString = timeDateFormatter.format(Date(message.timestamp))

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalAlignment = if (isUserMessage) Alignment.End else Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
        ) {
            if(!isUserMessage){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUser)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Recipient avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(Modifier.width(8.dp))
            }
            Column(
                horizontalAlignment = if(isUserMessage) Alignment.End else Alignment.Start
            ) {
                when (message.messageType){
                    MessageType.TEXT -> {
                        Box(
                            modifier = modifier
                                .widthIn(max = 280.dp)
                                .clip(bubbleShape)
                                .background(bubbleColor)
                                .padding(12.dp)
                        ){
                            Text(
                                text = message.content,
                                color = textColor,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    MessageType.IMAGE -> {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(message.content)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Image message",
                            contentScale = ContentScale.FillWidth,
                            modifier = modifier
                                .wrapContentHeight()
                                .width(300.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onImageClick(message.content) }
                        )
                    }
                    MessageType.FILE -> {
                        Card(
                            modifier = modifier
                                .clickable { onFileClick(message.content) }
                                .wrapContentWidth()
                                .widthIn(min = 100.dp, max = 320.dp)
                                .height(80.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp)),
                            colors = CardDefaults.cardColors(
                                containerColor = bubbleFileColor
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .padding(4.dp)
                                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.file_icon),
                                        contentDescription = "File",
                                        tint = textColor
                                    )
                                }

                                Spacer(Modifier.width(5.dp))

                                Column(
                                    modifier = Modifier
                                        .padding(top = 3.dp, bottom = 3.dp)
                                ) {
                                    Text(
                                        text = message.fileName ?: "File",
                                        color = textColor,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    message.fileSize?.let { size ->
                                        val fileSizeText = when {
                                            size < 1024 -> "$size B"
                                            size < 1024 * 1024 -> "${size / 1024} KB"
                                            else -> "${size / (1024 * 1024)} MB" // Sửa 11024 thành 1024
                                        }
                                        Text(
                                            text = fileSizeText,
                                            color = textColor.copy(alpha = 0.7f),
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }

                                Spacer(Modifier.width(5.dp))
                            }
                        }
                    }
                }

                Spacer(modifier = modifier.height(2.dp))

                Text(
                    text = timeString,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.7f
                    ),
                    modifier = modifier.padding(horizontal = 4.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatBubble(){
    Column {
        ChatBubble(
            message = ChatMessage("1","1","2","Xin chào",MessageType.TEXT,2,true),
            imageUser = "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg",
            isUserMessage = true
        )
        ChatBubble(
            message = ChatMessage("2","1","2","https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg",MessageType.IMAGE,2,true),
            imageUser = "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg",
            isUserMessage = true
        )
        ChatBubble(
            message = ChatMessage("3","1","2", "test",MessageType.FILE, 1,true,"Tài liệu", 290),
            imageUser = "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg",
            isUserMessage = false
        )
        ChatBubble(
            message = ChatMessage("1","1","2","Xin chào",MessageType.TEXT,2,true),
            imageUser = "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg",
            isUserMessage = false
        )
    }
}