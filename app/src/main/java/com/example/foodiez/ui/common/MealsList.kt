package com.example.foodiez.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.domain.product.Product
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.home.HomeViewModel
import com.example.foodiez.ui.product.ProductSource
import com.example.foodiez.ui.theme.Dark
import com.example.foodiez.ui.theme.Gray
import com.example.foodiez.ui.theme.Red
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableMealCard(product: Product, onClick: () -> Unit, onSwipe: () -> Unit) {
    RevealSwipe(
        shape = RoundedCornerShape(8.dp),
        directions = setOf(RevealDirection.EndToStart),
        closeOnBackgroundClick = true,
        backgroundCardEndColor = Red,
        hiddenContentEnd = {
            IconButton(onClick = { onSwipe() }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delete product",
                    tint = Color.White,
                    modifier = Modifier.padding(horizontal = 25.dp)
                )
            }
        }
    ) {
        MealCard(product, onClick)
    }
}

@Composable
fun MealCard(product: Product, onClick: (() -> Unit)) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
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
                    maxLines = 1,
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "${
                            product.caloriesByQuantity().toInt()
                        } kcal • ${product.quantity} g/ml",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}