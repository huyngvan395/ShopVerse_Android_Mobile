package com.example.shopverse.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopverse.ui.theme.MainColor


@Composable
fun CustomButton(
    text: String,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            contentColor = MainColor,
            containerColor = MainColor,
            disabledContentColor = MainColor,
            disabledContainerColor = MainColor
        ),
    ) {
        if(isActive){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(22.dp)
                    .padding(end = 8.dp),
                strokeWidth = 3.dp,
                color = Color.White
            )
        }
        Text(
            text  = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(5.dp),
            fontFamily = FontFamily.Default
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButton(){
    CustomButton(
        text = "Đăng nhập",
        onClick = {},
        modifier = Modifier.padding(5.dp),
        true
    )
}