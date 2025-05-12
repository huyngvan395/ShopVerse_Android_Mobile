package com.example.shopverse.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopverse.data.models.Category
import com.example.shopverse.ui.theme.MainColor

@Composable
fun CategoryFilterChips(
    categories: List<Category>,
    selectedIdCategory: Int?,
    onCategorySelected: (Int?) -> Unit,
    modifier: Modifier = Modifier
){
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier.horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach{category ->
            FilterChip(
                selected = selectedIdCategory == category.id,
                onClick = {onCategorySelected(null)},
                label = {
                    Text(
                        category.name,
                        color = MainColor
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MainColor.copy(alpha = 0.2f),
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selectedIdCategory == category.id,
                    borderColor = MainColor.copy(alpha = 0.9f),
                    selectedBorderColor = MainColor.copy(alpha = 0.2f)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryFilterChips(){
    val categories = listOf(
        Category(1, "Áo"),
        Category(1, "Quần"),
        Category(1,"Điện thoại")
    )

    CategoryFilterChips(categories, 1, onCategorySelected = {})

}