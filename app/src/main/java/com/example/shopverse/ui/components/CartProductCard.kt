package com.example.shopverse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shopverse.R
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.ui.theme.starColor
import kotlinx.coroutines.delay
import java.math.BigDecimal

@Composable
fun CartProductCard(
    isSelected: Boolean,
    cartItem: CartItem,
    rating: Double,
    reviews: Int,
    modifier: Modifier = Modifier,
    onClick: ()->Unit ={},
    onQuantityChange: (Int) -> Unit ={},
    onRemove: () -> Unit = {},
    onSelected: (Int) -> Unit = {}
) {
    var checked by remember { mutableStateOf(isSelected) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var quantity by remember { mutableIntStateOf(cartItem.quantity) }
    var errorMsg  by remember { mutableStateOf("") }

    LaunchedEffect(quantity) {
        delay(600)
        if (quantity != cartItem.quantity && quantity>=1){
            onQuantityChange(quantity)
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp, end = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onSelected(cartItem.id)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MainColor,
                    checkmarkColor = Color.White,
                    uncheckedColor = Color.Gray,
                    disabledUncheckedColor = MainColor.copy(alpha = 0.5f),
                    disabledCheckedColor = MainColor.copy(alpha = 0.5f)
                ),
            )
            // Ảnh
            Card(
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                AsyncImage(
                    model = cartItem.product.imageUrl,
                    contentDescription = cartItem.product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .width(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cartItem.product.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "đ${cartItem.product.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MainColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if(reviews>0){
                        Text(
                            text = "$rating",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MainColor
                        )
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = starColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text =if(reviews==0) "(Chưa có đánh giá nào)" else "($reviews đánh giá)",
                        fontSize = 16.sp,
                        color = MainColor
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Điều chỉnh số lượng
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .background(
                                color = Color.Gray.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {
                                if(quantity>1){
                                    quantity -=1
                                }else {
                                    onRemove()
                                }
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.remove),
                                contentDescription = null,
                                tint = Color.Gray.copy(0.8f),
                            )
                        }
                        VerticalDivider(
                            modifier = Modifier.padding(0.dp).height(50.dp),
                            thickness = 1.dp,
                            color = Color.Gray.copy(0.5f)
                        )
                        TextField(
                            value = quantity.toString(),
                            onValueChange = {
                                val newQuantity = it.toIntOrNull()
                                if(newQuantity!=null &&  newQuantity>0 && newQuantity<=cartItem.product.stock ){
                                    quantity = newQuantity
                                } else {
                                    quantity = 1
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .widthIn(min = 20.dp, max = 70.dp)
                                .focusRequester(focusRequester)
                                .pointerInput(Unit) {
                                    detectTapGestures {
                                        focusManager.clearFocus()
                                    }
                                },
                            textStyle = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                color = MainColor,
                                textAlign = TextAlign.Center
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true,
                        )
                        VerticalDivider(
                            modifier = Modifier.padding(0.dp).height(50.dp),
                            thickness = 1.dp,
                            color = Color.Gray.copy(0.5f),
                        )
                        IconButton(
                            onClick = {
                                if(quantity<cartItem.product.stock){
                                    quantity+=1
                                }else{
                                    errorMsg ="Số lượng vượt quá mức tối đa"
                                }
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.Gray.copy(0.8f)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = onRemove,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(5.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove",
                                    tint = MainColor
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
