package com.example.foodiez.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodiez.ui.home.HomeScreen
import com.example.foodiez.ui.product.ProductScreen
import com.example.foodiez.ui.search.SearchScreen
import com.example.foodiez.ui.settings.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Product : Screen("product")
    object Settings : Screen("settings")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Search.route,
            enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(400)) },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(400)) },
        ) {
            SearchScreen(navController, hiltViewModel())
        }
        composable(
            route = Screen.Product.route,
            enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(400)) },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(400)) },
        ) {
            ProductScreen(navController, hiltViewModel())
        }
        composable(
            route = Screen.Settings.route,
            enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(400)) },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(400)) },
        ) {
            SettingsScreen(navController)
        }
    }
}