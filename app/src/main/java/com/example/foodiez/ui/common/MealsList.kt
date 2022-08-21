package com.example.foodiez.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.domain.product.Product
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.product.ProductSource
import com.example.foodiez.ui.theme.Dark
import com.example.foodiez.ui.theme.Gray

@Composable
fun MealsList(navController: NavController, mealType: MealType, products: List<Product>) {
    val filteredProducts = products.filter { it.type == mealType }

    if(filteredProducts.isNotEmpty()) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp, bottom = 6.dp)
            ) {
                Text(
                    text = mealType.tag,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add product",
                    modifier = Modifier.size(20.dp),
                    tint = Dark
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            filteredProducts.forEach {
                MealCard(product = it) {
                    it.id?.let { id ->
                        navController.navigate(
                            Screen.Product.navigationLink(
                                ProductSource.LOCAL,
                                id.toLong()
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MealCard(product: Product, onClick: (() -> Unit)) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        backgroundColor = Gray
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(product.imageURL),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "${product.caloriePer100} kcal • 100 g/ml",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}