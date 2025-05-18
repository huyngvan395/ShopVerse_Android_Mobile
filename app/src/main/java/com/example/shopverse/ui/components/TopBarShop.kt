package com.example.shopverse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopverse.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarShop(
    modifier: Modifier = Modifier,
    onSearchChange: (String) -> Unit = {},
    searchText: String = "",
    onCartClick: () -> Unit = {},
    cartCount: Int,
    onSearch:() ->Unit={}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .background(MainColor),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = searchText,
                onValueChange = onSearchChange,
                placeholder = { Text(
                    "Tìm sản phẩm...",
                    fontSize = 20.sp
                ) },
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp)
                    .shadow(
                        elevation = 8.dp,
                        clip = true
                    ),
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = androidx.compose.ui.graphics.Color.White,
                    focusedContainerColor = androidx.compose.ui.graphics.Color.White,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Tìm kiếm",
                        tint = androidx.compose.ui.graphics.Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                    }
                ),
            )

            Spacer(modifier = Modifier.width(8.dp))

            CartButton(cartCount,onCartClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBarShop(){
    TopBarShop(cartCount = 5)
}