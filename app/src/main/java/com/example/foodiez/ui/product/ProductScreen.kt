package com.example.foodiez.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.ui.theme.*

@Composable
fun ProductScreen(navController: NavController, viewModel: ProductViewModel) {
    val product = viewModel.product?.data
    Box(
        modifier = Modifier
            .background(CreamWhite2)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ProductHeader(
                url = product?.imageURL,
                name = product?.productName,
                brand = product?.brands
            )
            ProductMacroNutriments(
                product?.nutriments?.energyKcal,
                product?.nutriments?.carbohydrates,
                product?.nutriments?.proteins,
                product?.nutriments?.fat
            )
            ProductQuantity()
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
fun ProductQuantity() {
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        var value by remember { mutableStateOf("100") }
        var meal by remember { mutableStateOf("Breakfast") }

        val options = listOf("Breakfast", "Lunch", "Snack", "Diner",)
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Quantity") },
            singleLine = true,
            trailingIcon = { Text("g/ml") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Dark, unfocusedBorderColor = Dark),
            modifier = Modifier.weight(1f)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Meal type") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Dark, unfocusedBorderColor = Dark),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
}


//@Preview()
//@Composable
//fun SearchViewPreview() {
//    ProductScreen(hiltViewModel())
//}