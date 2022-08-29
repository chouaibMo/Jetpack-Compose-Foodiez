package com.example.foodiez.ui.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.domain.statistic.Statistic
import com.example.foodiez.navigation.Screen
import com.example.foodiez.ui.common.MealsList
import com.example.foodiez.ui.theme.CreamWhite2
import com.example.foodiez.ui.theme.Dark
import com.example.foodiez.ui.theme.Gray
import com.lemillion.android.fab.FabItem
import com.lemillion.android.fab.MultiFabState
import com.lemillion.android.fab.MultiFloatingActionButton


// TODO : add alpha when FAB is expanded
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val products by viewModel.products.collectAsState(initial = emptyList())
    val stats by remember { mutableStateOf(viewModel.stats) }
    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    Scaffold(
        backgroundColor = CreamWhite2,
        modifier = Modifier
            .background(CreamWhite2)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    toState = MultiFabState.COLLAPSED
                })
            },
        floatingActionButton = {
            HomeFloatingActionButton(navController)
        }
    ) {
        val alpha = if (toState == MultiFabState.EXPANDED) 0.4f else 1f

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .alpha(animateFloatAsState(alpha).value)
        ) {
            item { HomeHeader(navController) }
            item { Spacer(modifier = Modifier.size(25.dp)) }
            item { CaloriesProgress(stats) }
            item { Spacer(modifier = Modifier.size(25.dp)) }
            item { MacroNutrimentsCard(stats) }
            item { Spacer(modifier = Modifier.size(25.dp)) }
            item { MealsList(navController, MealType.BREAKFAST, products, viewModel) }
            item { MealsList(navController, MealType.LUNCH, products, viewModel) }
            item { MealsList(navController, MealType.SNACK, products, viewModel) }
            item { MealsList(navController, MealType.DINER, products, viewModel) }
        }
    }
}

@Composable
fun HomeFloatingActionButton(navController: NavController) {
    MultiFloatingActionButton(
        fabIcon = Icons.Filled.Add,
        items = listOf(
            FabItem(Icons.Filled.QrCodeScanner, "Scan product") {
                navController.navigate(Screen.Scan.route)
            },
            FabItem(Icons.Default.Search, "Search product") {
                navController.navigate(Screen.Search.route)
            }
        ),
        showLabels = false
    )
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
                //TODO : set real profile
                painter = rememberImagePainter("https://media.istockphoto.com/photos/smiling-indian-man-looking-at-camera-picture-id1270067126?k=20&m=1270067126&s=612x612&w=0&h=ZMo10u07vCX6EWJbVp27c7jnnXM2z-VXLd-4maGePqc="),
                contentDescription = "",
                contentScale = ContentScale.Crop,
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
fun CaloriesProgress(stats: Statistic) {
    val left = 2500 - stats.totalCalories
    val progress = stats.totalCalories.toFloat() / 2500f
    Column(verticalArrangement = Arrangement.Center) {
        LinearProgressIndicator(
            progress = progress,
            color = MaterialTheme.colors.primary,
            backgroundColor = Gray,
            modifier = Modifier
                .fillMaxWidth()
                .size(20.dp)
                .graphicsLayer {
                    shape = RoundedCornerShape(8.dp)
                    clip = true
                }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$left calories left", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(text = "${stats.totalCalories} kcal", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}

