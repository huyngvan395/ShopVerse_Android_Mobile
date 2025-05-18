package com.example.shopverse.ui.screen.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopverse.data.models.Address
import com.example.shopverse.ui.theme.MainColor
import com.example.shopverse.viewmodel.shop.SelectAddressViewModel

@Composable
fun SelectAddressScreen(
    selectAddressViewModel: SelectAddressViewModel,
    navController: NavController
){
    var selectedAddress by remember { mutableStateOf<Address?>(null) }
    val listAddress by selectAddressViewModel.listAddress.collectAsState()
    Scaffold(
        topBar = {
            SelectAddressTopBar(onBackClick = {navController.popBackStack()})
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn() {
                items(listAddress){address->
                    AddressCard(
                        address = address,
                        isSelected = selectedAddress?.id == address.id,
                        onSelect = { selectedAddress = it }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAddressTopBar(
    onBackClick:()->Unit={}
){
    TopAppBar(
        title = {
            Text("Chọn địa chỉ nhận hàng")
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun AddressCard(
    address: Address,
    isSelected: Boolean,
    onSelect: (Address) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { onSelect(address) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MainColor.copy(alpha = 0.1f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onSelect(address) },
                modifier = Modifier.padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = address.name,
                        fontSize = 16.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = address.phone,
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = address.address,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

