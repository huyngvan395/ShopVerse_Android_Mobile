package com.example.shopverse.viewmodel.shop

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.models.request.AddReviewRequest
import com.example.shopverse.data.repositories.ReviewRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody
import java.io.InputStream

class WriteReviewViewModel(
    private val reviewRepository: ReviewRepository
):ViewModel() {


    fun addReview(context: Context,addReviewRequest: AddReviewRequest){
        viewModelScope.launch {
            val productId:RequestBody = addReviewRequest.productId.toString().toRequestBody("text/plain".toMediaType())
            val content: RequestBody = addReviewRequest.content.toRequestBody("text/plain".toMediaType())
            val rating:RequestBody = addReviewRequest.rating.toString().toRequestBody("text/plain".toMediaType())
            val userId:RequestBody = addReviewRequest.userId.toString().toRequestBody("text/plain".toMediaType())
            val images: List<MultipartBody.Part>? = addReviewRequest.images?.mapIndexed{index, uri ->
                val inputStream:InputStream? = context.contentResolver.openInputStream(uri)
                val bytes= inputStream?.readBytes()
                inputStream?.close()
                val requestBody = bytes?.toRequestBody("image/*".toMediaType())

                requestBody?.let {
                    val timeStamp = System.currentTimeMillis()
                    val fileName = "review_${timeStamp}_$index"
                    val mimeType = context.contentResolver.getType(uri)
                    val extension = when (mimeType){
                        "image/png" -> "png"
                        "image/jpeg" -> "jpg"
                        else -> "jpg"
                    }
                    val file = "$fileName.$extension"
                    MultipartBody.Part.createFormData("image",file,requestBody)
                }
            }?.filterNotNull()
            if (images != null) {
                reviewRepository.addReview(productId,rating,content,userId,images)
            }
        }
    }
}