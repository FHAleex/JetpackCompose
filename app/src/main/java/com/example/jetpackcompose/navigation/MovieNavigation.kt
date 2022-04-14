package com.example.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.screen.DetailScreen
import com.example.jetpackcompose.screen.FavoritesScreen
import com.example.jetpackcompose.screen.HomeScreen
import com.example.jetpackcompose.viewmodels.FavoritesViewModel


@Composable
fun MovieNavigation() {

    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {

        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController, viewModel = favoritesViewModel)}
        composable(route = MovieScreens.DetailScreen.name + "/{movieId}", arguments = listOf(navArgument("movieId") {
            type = NavType.StringType
        })
        ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"), viewModel = favoritesViewModel)}

        composable(route = MovieScreens.FavoritesScreen.name) { FavoritesScreen(navController = navController, viewModel = favoritesViewModel)}
    }
}