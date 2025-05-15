package com.example.shopverse.data.models.request

import android.net.Uri

data class AddReviewRequest(
    val productId:Int,
    val rating:Int,
    val content:String,
    val userId:Int,
    val images:List<Uri>?=null
)