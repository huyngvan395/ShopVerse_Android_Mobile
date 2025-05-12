package com.example.shopverse.data.models

data class ChatMessage(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val messageType: MessageType,
    val timestamp: Long,
    val isRead: Boolean,
    val fileName: String?= null,
    val fileSize: Long?= null
)

enum class MessageType{
    TEXT,
    IMAGE,
    FILE,
}
