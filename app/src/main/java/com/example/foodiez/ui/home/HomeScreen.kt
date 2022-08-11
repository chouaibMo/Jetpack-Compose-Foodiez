package com.example.foodiez.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.common.MealsList
import com.example.foodiez.ui.theme.CreamWhite2
import com.example.foodiez.ui.theme.Dark


@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(CreamWhite2)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(25.dp)) {
            item { HomeHeader(navController) }
            item { CaloriesProgress() }
            item { MacroNutrimentsCard() }
            item { MealsList(navController, "Breakfast", hiltViewModel()) }
            item { MealsList(navController,"Lunch", hiltViewModel()) }
            item { MealsList(navController,"Snack", hiltViewModel()) }
            item { MealsList(navController,"Diner", hiltViewModel()) }
        }
    }
}


@Composable
fun HomeHeader(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter("https://www.menshairstylestoday.com/wp-content/uploads/2021/07/Messy-Hair.jpg"),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, Dark, CircleShape)
                    .clickable {
                        navController.navigate(Screen.Settings.route)
                    }
            )
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "Welcome back,", fontSize = 12.sp)
                Text(text = "John Doe", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Settings",
            modifier = Modifier
                .size(34.dp)
                .clickable {
                    navController.navigate(Screen.Search.route)
                }
        )
    }
}

@Composable
fun CaloriesProgress() {
    Column(verticalArrangement = Arrangement.Center) {
        LinearProgressIndicator(
            progress = 0.7f,
            color = MaterialTheme.colors.primary,
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .size(12.dp)
                .graphicsLayer {
                    shape = RoundedCornerShape(14.dp)
                    clip = true
                }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "240 calories left", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(text = "2200 kcal", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}

