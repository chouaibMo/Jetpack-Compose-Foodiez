package com.example.foodiez.ui.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CircleNotifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.lemillion.android.fab.MultiFloatingActionButton


// TODO : add alpha when FAB is expanded
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val products by viewModel.products.collectAsState(initial = emptyList())
    val stats by  viewModel.stats.collectAsState()

    //var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }

    Scaffold(
        backgroundColor = CreamWhite2,
        modifier = Modifier
            .background(CreamWhite2)
            .fillMaxSize()
            .pointerInput(Unit) {
//                detectTapGestures(onTap = {
//                    toState = MultiFabState.COLLAPSED
//                })
            },
        floatingActionButton = {
            HomeFloatingActionButton(navController)
        }
    ) {
        //val alpha = if (toState == MultiFabState.EXPANDED) 0.4f else 1f

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                //.alpha(animateFloatAsState(alpha).value)
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
                painter = rememberImagePainter("https://images.pexels.com/photos/2531553/pexels-photo-2531553.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, Dark, CircleShape)
                    .clickable {
                        navController.navigate(Screen.Settings.route)
                    }
            )
            Column(verticalArrangement = Arrangement.spacedBy((-4).dp, Alignment.CenterVertically)) {
                Text(text = "Welcome back,", color = Color.DarkGray, fontSize = 14.sp)
                Text(text = "John Doe", color = Color.DarkGray, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
        Icon(
            imageVector = Icons.Outlined.CircleNotifications,
            contentDescription = "Settings",
            tint = Dark,
            modifier = Modifier
                .size(34.dp)
                .clickable {}
        )
    }
}

@Composable
fun CaloriesProgress(stats: Statistic) {
    //TODO : use preferences for max calories
    val left = 2500 - stats.totalCalories

    val progress: Float by animateFloatAsState(
        targetValue = stats.totalCalories.toFloat() / 2500f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

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
            Text(text = "${stats.totalCalories} kcal", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(text = "$left kcal left", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}

