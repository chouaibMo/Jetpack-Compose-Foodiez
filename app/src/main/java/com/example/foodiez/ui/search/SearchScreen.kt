package com.example.foodiez.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.common.MealCard
import com.example.foodiez.ui.product.ProductSource
import com.example.foodiez.ui.theme.CreamWhite2

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {
    val products = viewModel.productsList?.products
    Box(
        modifier = Modifier
            .background(CreamWhite2)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            products?.forEach { product ->
                item {
                    MealCard(data = product) {
                        navController.navigate(Screen.Product.navigationLink(ProductSource.REMOTE, product.productID.toLong()))
                    }
                }
            }
        }
    }
}