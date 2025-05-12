package com.example.shopverse.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shopverse.R
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    rating: Float,
    reviewCount: Int = 0,
    maxRating: Int = 5,
    starSize: Modifier = Modifier.size(16.dp),
    starColor: Color = Color(0xFFFFC107),
    showReviewCount: Boolean = true
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..maxRating) {
            val starIcon:  Any = when {
                i <= floor(rating) -> Icons.Default.Star
                i > ceil(rating) -> painterResource(R.drawable.star_border)
                else -> painterResource(R.drawable.star_half)
            }

            when(starIcon){
                is ImageVector ->{
                    Icon(
                        imageVector = starIcon,
                        contentDescription = null,
                        tint = starColor,
                        modifier = starSize
                    )
                }
                is Painter  ->{
                    Icon(
                        painter = starIcon,
                        contentDescription = null,
                        tint = starColor,
                        modifier = starSize
                    )
                }
            }

        }

        if (showReviewCount && reviewCount > 0) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "($reviewCount)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

