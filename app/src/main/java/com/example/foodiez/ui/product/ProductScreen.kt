package com.example.foodiez.ui.product

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.data.utils.Constants
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.domain.product.Product
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.theme.*

@Composable
fun ProductScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val product = viewModel.product.collectAsState()

    val productData = product.value?.data
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            SaveProductButton {
                viewModel.saveProduct(product.value)
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
        },
        backgroundColor = CreamWhite2,
        modifier = Modifier
            .background(CreamWhite2)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ProductHeader(
                url = productData?.imageURL,
                name = productData?.productName,
                brand = productData?.brands
            )
            ProductMacroNutriments(
                productData?.nutriments?.energyKcal,
                productData?.nutriments?.carbohydrates,
                productData?.nutriments?.proteins,
                productData?.nutriments?.fat
            )
            ProductQuantity(product.value)
        }
    }

}

@Composable
fun ProductHeader(url: String?, name: String?, brand: String?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(url),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = name ?: "N/A",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
        Text(text = brand ?: "N/A", fontSize = 12.sp)
    }
}

@Composable
fun ProductMacroNutriments(calories: Double?, carbs: Double?, proteins: Double?, fats: Double?) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        MacroNutriment("Calories (kcal)", calories, Dark)
        MacroNutriment("Carbs (g)", carbs, Green100)
        MacroNutriment("Proteins (g)", proteins, Blue100)
        MacroNutriment("Fats (g)", fats, Orange100)
    }
}

@Composable
fun MacroNutriment(label: String, value: Double?, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$value",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = color
        )
        Text(text = label, fontSize = 14.sp)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductQuantity(product: Product?) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        var value by remember { mutableStateOf("100") }

        val options = MealType.values().map { it.tag }
        var expanded by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Quantity") },
            singleLine = true,
            trailingIcon = { Text("g/ml") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Dark,
                unfocusedBorderColor = Dark
            ),
            modifier = Modifier.weight(1f)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                readOnly = true,
                value = product?.type?.tag ?: "Select meal type",
                onValueChange = {},
                label = { Text("Meal type") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Dark,
                    unfocusedBorderColor = Dark
                ),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selected ->
                    DropdownMenuItem(
                        onClick = {
                            product?.type = MealType.valueOf(selected.uppercase())
                            expanded = false
                        }
                    ) {
                        Text(text = selected)
                    }
                }
            }
        }
    }
}

@Composable
fun SaveProductButton(onClick: (() -> Unit)) {
    FloatingActionButton(
        backgroundColor = Green100,
        onClick = { onClick() },
        modifier = Modifier.size(60.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "add product",
            tint = Color.White,
            modifier = Modifier.size(35.dp)
        )
    }
}


//@Preview()
//@Composable
//fun SearchViewPreview() {
//    ProductScreen(hiltViewModel())
//}