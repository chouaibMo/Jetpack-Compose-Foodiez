package com.example.foodiez.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiez.ui.theme.Blue100
import com.example.foodiez.ui.theme.Green100
import com.example.foodiez.ui.theme.Orange100

@Composable
fun ProductMacroNutriments(calories: Double?, carbs: Double?, proteins: Double?, fats: Double?) {
    Card(shape = RoundedCornerShape(12.dp), backgroundColor = MaterialTheme.colors.surface) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            MacroNutriment("Calories (kcal)", calories, MaterialTheme.colors.onSurface)
            MacroNutriment("Carbs (g)", carbs, Green100)
            MacroNutriment("Proteins (g)", proteins, Blue100)
            MacroNutriment("Fats (g)", fats, Orange100)
        }
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
        Text(text = label, fontSize = 12.sp, color = MaterialTheme.colors.onSurface)
    }
}