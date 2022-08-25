package com.example.foodiez.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.foodiez.ui.home.HomeScreen
import com.example.foodiez.ui.product.ProductScreen
import com.example.foodiez.ui.product.ProductSource
import com.example.foodiez.ui.scan.ScanScreen
import com.example.foodiez.ui.search.SearchScreen
import com.example.foodiez.ui.settings.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

const val ARG_PRODUCT_ID = "arg_product_id"
const val ARG_PRODUCT_SOURCE = "arg_product_source"

internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Scan : Screen("scan")
    object Search : Screen("search")
    object Settings : Screen("settings")
    object Product : Screen("product/{$ARG_PRODUCT_SOURCE}/{$ARG_PRODUCT_ID}") {
        fun navigationLink(source : ProductSource, id : Long) : String =
            "product/$source/$id"
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Scan.route) {
            ScanScreen(navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController, hiltViewModel())
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(navController)
        }
        composable(
            route = Screen.Product.route,
            arguments = listOf(
                navArgument(ARG_PRODUCT_SOURCE) { type = NavType.StringType },
                navArgument(ARG_PRODUCT_ID) { type = NavType.LongType }
            ),
        ) {
            ProductScreen(navController, hiltViewModel(), )
        }
    }
}