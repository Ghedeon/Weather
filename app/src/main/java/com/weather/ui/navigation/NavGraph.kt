package com.weather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weather.ui.screen.weather.WeatherScreen

@Composable
fun InitNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Weather.route
    ) {
        composable(Screen.Weather.route) {
            WeatherScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Weather : Screen("weather")
}