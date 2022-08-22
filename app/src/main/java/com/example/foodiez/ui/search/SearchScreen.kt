package com.example.foodiez.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.common.MealCard
import com.example.foodiez.ui.product.ProductSource
import com.example.foodiez.ui.theme.CreamWhite2
import com.example.foodiez.ui.theme.Gray
import com.example.foodiez.ui.theme.Green100

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {
    val products = viewModel.productsList.collectAsState().value

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .background(CreamWhite2)
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "back",
                    tint = Green100
                )
            }

            SearchBar(onValueChanged = { viewModel.searchRequested(it) })
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            products.forEach { product ->
                item {
                    MealCard(
                        product = product,
                        onClick = {
                            navController.navigate(
                                Screen.Product.navigationLink(
                                    ProductSource.REMOTE,
                                    product.remoteId.toLong()
                                )
                            )
                        })
                }
            }
        }
    }
}


@Composable
fun SearchBar(onValueChanged: ((String) -> Unit)) {
    var state by remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        elevation = 3.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        BasicTextField(
            value = state,
            onValueChange = {
                state = it
                onValueChanged(it.text)
            },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .background(Gray, RoundedCornerShape(8.dp))
                .height(45.dp)
                .fillMaxWidth(),
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                        tint = Green100
                    )
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state == TextFieldValue(""))
                            Text("Search...")
                        innerTextField()
                    }
                    if (state != TextFieldValue("")) {
                        IconButton(
                            onClick = { state = TextFieldValue("") },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "reset search bar",
                                tint = Green100
                            )
                        }
                    }
                }
            }
        )
    }
}

//@Preview()
//@Composable
//fun SearchBarPreview() {
//   SearchBar()
//}