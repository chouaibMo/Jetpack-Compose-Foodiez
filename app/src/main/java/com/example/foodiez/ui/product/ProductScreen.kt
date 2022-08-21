package com.example.foodiez.ui.product

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.data.utils.Constants.Companion.TAG
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.theme.*

@Composable
fun ProductScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val state = viewModel.state
    val focusManager = LocalFocusManager.current

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            SaveProductButton {
                viewModel.saveProduct(state.product)
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
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        ) {
            ProductHeader(
                url = state.product?.imageURL,
                name = state.product?.name,
                brand = state.product?.brand
            )
            ProductMacroNutriments(
                state.product?.caloriePer100,
                state.product?.carbsPer100,
                state.product?.proteinsPer100,
                state.product?.fatsPer100
            )
            ProductQuantity(state)
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
            text = name ?: "--",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
        Text(text = brand ?: "--", fontSize = 12.sp)
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
fun ProductQuantity(state: ProductState) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        val focusManager = LocalFocusManager.current
        var quantity by remember { mutableStateOf(0) }
        var expanded by remember { mutableStateOf(false) }
        val options = MealType.values().map { it.tag }

        quantity = state.product?.quantity ?: 0

        OutlinedTextField(
            value = quantity.toString(),
            onValueChange = {
                quantity = if (it.isBlank()) 0 else it.toInt()
                Log.e(TAG, "changed: $it")
                state.product?.quantity = quantity
            },
            label = { Text("Quantity") },
            singleLine = true,
            trailingIcon = { Text("g/ml") },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
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
                value = state.product?.type?.tag ?: "Select..",
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
                            focusManager.clearFocus()
                            state.product?.type = MealType.valueOf(selected.uppercase())
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