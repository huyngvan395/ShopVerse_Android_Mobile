package com.example.shopverse.ui.screen.shop

import android.text.Html
import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shopverse.MyApplication
import com.example.shopverse.R
import com.example.shopverse.data.models.Product
import com.example.shopverse.data.models.request.AddToCartRequest
import com.example.shopverse.data.models.request.AddToFavouriteRequest
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.viewmodel.shop.ProductDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.delay

@Composable
fun ProductDetailScreen(
    productDetailViewModel: ProductDetailViewModel,
    navController: NavController
) {
    val product by productDetailViewModel.product.collectAsState()
    var showQuantityDialog by remember { mutableStateOf(false) }
    var actionType by remember { mutableStateOf("") }
    var contentActionType by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { ProductDetailTopBar(
            onBackClick = {
                navController.popBackStack()
            }
        ) },
        bottomBar = {
            if(!showQuantityDialog){
                ProductDetailBottomBar(
                    onAddToCart = {
                        actionType = "add_to_cart"
                        contentActionType = "Thêm vào giỏ hàng"
                        showQuantityDialog = true
                    },
                    onBuyNow = {
                        actionType = "buy_now"
                        contentActionType = "Mua ngay"
                        showQuantityDialog = true
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = product?.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .height(420.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product?.name.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                )
                //Button Favourite
                IconButton(
                    onClick = {
                        productDetailViewModel.addToFavourite(AddToFavouriteRequest(MyApplication.appContainer.getCurrentUser()!!.id,product!!.id))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = "Giá: ${product?.price}",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = product?.description.toString(),
//                fontSize = 16.sp,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
            product?.description?.let { desc ->
                AndroidView(
                    factory = { context ->
                        TextView(context).apply {
                            text = Html.fromHtml(desc, Html.FROM_HTML_MODE_COMPACT)
                            textSize = 16f
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }

        }
    }

    if (showQuantityDialog) {
        product?.let {
            QuantityBottomSheetDialog(
                product = it,
                onDismiss = { showQuantityDialog = false },
                onConfirm = { quantity ->
                    showQuantityDialog = false
                    if (actionType == "add_to_cart") {
                        Log.d("addtocart",AddToCartRequest(product!!.id,quantity,MyApplication.appContainer.getCurrentUser()!!.id).toString())
                        productDetailViewModel.addToCart(AddToCartRequest(product!!.id,quantity,MyApplication.appContainer.getCurrentUser()!!.id))
                    } else if(actionType == "buy_now") {
                        navController.navigate("checkout/buyNow/${product?.id}/${quantity}")
                    }
                },
                type = contentActionType,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailTopBar(
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = "Chi tiết sản phẩm") },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0x00FFFFFF)
        ),
        modifier = Modifier
            .shadow(
                elevation = 1.dp
            )
    )
}

@Composable
fun ProductDetailBottomBar(
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(50.dp)
    ) {
        Button(
            onClick = { onAddToCart() },
            modifier = Modifier.weight(1f).fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray.copy(alpha = 0.1f)
            ),
            shape = RectangleShape
        ) {
            Icon(
                painter = painterResource(R.drawable.add_shopping_cart),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
        Button(
            onClick = { onBuyNow() },
            modifier = Modifier.weight(1f).fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor
            ),
            shape = RectangleShape
        ) {
            Text(
                "Mua ngay",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

//@Composable
//fun QuantityDialog(
//    onDismiss: () -> Unit,
//    onConfirm: (quantity: Int) -> Unit,
//    type: String
//) {
//
//    Dialog(
//        onDismissRequest = { onDismiss() },
//        properties = DialogProperties(
//            usePlatformDefaultWidth = false
//        ),
//    ) {
//
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantityBottomSheetDialog(
    product: Product,
    onDismiss: () -> Unit,
    onConfirm: (quantity: Int) -> Unit,
    type: String
){
    var quantity by remember { mutableIntStateOf(1) }
    val sheetState = rememberModalBottomSheetState()
    var errorMsg  by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    if (errorMsg.isNotEmpty()) {
        LaunchedEffect(errorMsg) {
            delay(2000)
            errorMsg = ""
        }
    }

    ModalBottomSheet(
        onDismissRequest = {onDismiss()},
        sheetState = sheetState
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        AsyncImage(
                            model = product.imageUrl,
                            contentDescription = product.name,
                            modifier = Modifier.height(100.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "${product.price}đ",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,

                        )
                        Text(
                            text = "Kho: ${product.stock}",
                            color = Color.Gray
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 15.dp),
                    thickness = 4.dp
                )
                // quantity
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Số lượng",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600)
                    )
                    Spacer(modifier = Modifier.width(60.dp))
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
                        Button(
                            onClick = {
                                if(quantity>1){
                                    quantity -= 1
                                } else {
                                    errorMsg =  "Số lượng không được bé hơn 1"
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            contentPadding = PaddingValues(0.dp)
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
                                if(newQuantity!=null &&  newQuantity>0 && newQuantity<=product.stock ){
                                    quantity = newQuantity
                                } else {
                                    errorMsg = "Số lượng không hợp lệ"
                                    quantity = 1
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 1.dp)
                                .width(80.dp)
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
                        Button(
                            onClick = {
                                if(quantity<product.stock){
                                    quantity +=1
                                }else{
                                    errorMsg = "Số lượng không hợp lệ"
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.Gray.copy(0.8f)
                            )
                        }
                    }

                }
                Text(
                    text = errorMsg,
                    color = Color.Red,
                    modifier = Modifier.padding(5.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { onConfirm(quantity) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                        .background(
                            shape = RoundedCornerShape(8.dp),
                            color = MainColor
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainColor,
                    )
                ) {
                    if(type == "Thêm vào giỏ hàng"){
                        Icon(
                            painter = painterResource(R.drawable.add_shopping_cart),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Text(
                        text = type,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}



