package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.R
import com.example.shopverse.data.models.CartItem
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.request.CreateOrderRequest
import com.example.shopverse.navigation.Screen
import com.example.shopverse.ui.components.OrderProductCard
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.viewmodel.shop.CheckoutViewModel
import java.math.BigDecimal

@Composable
fun CheckoutScreen(
    navController: NavController,
    checkoutViewModel: CheckoutViewModel,
    mode: String,
    productId: Int?,
    quantity: Int?
) {
    val listProduct by checkoutViewModel.listCheckout.collectAsState()
    val address by checkoutViewModel.address.collectAsState()

    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(productId) {
        if (mode == "buyNow" && productId != null) {
            product = checkoutViewModel.loadProduct(productId)
        }
    }

    Scaffold(
        topBar = { CheckoutTopBar(onBackClick = { navController.popBackStack() }) },
        bottomBar = {
            val total: BigDecimal = if (mode == "buyNow") {
                product?.price?.multiply(BigDecimal(quantity ?: 1)) ?: BigDecimal.ZERO
            } else {
                listProduct.fold(BigDecimal.ZERO) { acc, item ->
                    acc + item.product.price.multiply(BigDecimal(item.quantity))
                }
            }
            val totalQuantity = if (mode == "buyNow") quantity ?: 1 else listProduct.sumOf { it.quantity }

            CheckoutBottomBar(
                quantityProduct = totalQuantity,
                total = total,
                checkoutOnclick = {
                    if (mode == "buyNow" && product != null) {
                        checkoutViewModel.buyNow(CreateOrderRequest(product!!.id,quantity!!))
                    } else {
                        checkoutViewModel.checkoutFromCart()
                    }
                    navController.navigate(Screen.OrderSuccessScreen.route)
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if(address!=null){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            // navController.navigate("chooseAddress") hoặc hàm chọn địa chỉ
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn, // icon địa chỉ
                            contentDescription = "Location",
                            tint = MainColor,
                            modifier = Modifier.size(24.dp)
                        )

                        Column(
                            modifier = Modifier.padding(start = 12.dp)
                        ) {
                            Row {
                                Text(
                                    text = address!!.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = address!!.phone,
                                    fontSize = 14.sp
                                )
                            }

                            Text(
                                text = address!!.address,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Navigate",
                        tint = Color.Gray
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                        .clickable {
                            // navController.navigate("chooseAddress")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn, // icon thêm địa chỉ
                        contentDescription = "Add Address",
                        tint = MainColor,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = "Thêm địa chỉ nhận hàng",
                        fontSize = 16.sp,
                        color = MainColor,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
            if (mode == "buyNow" && product != null) {
                OrderProductCard(product = product!!, quantity = quantity ?: 1)
            } else if (mode == "fromCart") {
                LazyColumn {
                    items(listProduct.size) { index ->
                        val item = listProduct[index]
                        OrderProductCard(product = item.product, quantity = item.quantity)
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutTopBar(
    onBackClick:()->Unit={}
){
    TopAppBar(
        title = { Text("Thanh toán") },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun CheckoutBottomBar(
    quantityProduct:Int,
    total:BigDecimal,
    checkoutOnclick:()->Unit ={}
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Tổng số tiền ($quantityProduct sản phẩm)"
            )
            Text(
                text = "${total}đ"
            )
        }
        Button(
            onClick = {checkoutOnclick()},
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor
            ),
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Đặt hàng",
                color = Color.White,
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp
            )
        }
    }
}