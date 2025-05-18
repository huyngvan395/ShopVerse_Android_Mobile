package com.example.shopverse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shopverse.data.models.OrderItem
import com.example.shopverse.data.models.Product

@Composable
fun OrderProductCard(
    product: Product,
    quantity:Int,
    onClick: () -> Unit = {},
    modifier: Modifier=Modifier
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .height(105.dp),
        ) {
            Card(
                elevation = CardDefaults.elevatedCardElevation(5.dp),
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(110.dp)
                        .width(110.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(400),
                    maxLines = 2
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${product.price}đ",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "x$quantity",
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}
