package com.example.foodiez.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.domain.product.Product
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.home.HomeViewModel
import com.example.foodiez.ui.product.ProductSource
import com.example.foodiez.ui.theme.Dark

@Composable
fun MealsList(navController: NavController, mealType: MealType, products: List<Product>, viewModel: HomeViewModel) {
    val filteredProducts = products.filter { it.type == mealType }

    if (filteredProducts.isNotEmpty()) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp, bottom = 6.dp)
            ) {
                Text(text = mealType.tag, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add product",
                    modifier = Modifier.size(20.dp),
                    tint = Dark
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        ) {
            filteredProducts.forEach {
                SwipeableMealCard(
                    product = it,
                    onSwipe = { viewModel.removeProduct(it) },
                    onClick = {
                        it.id?.let { id ->
                            navController.navigate(
                                Screen.Product.navigationLink(
                                    ProductSource.LOCAL,
                                    id.toLong()
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}