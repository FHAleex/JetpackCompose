package com.example.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.screen.DetailScreen
import com.example.jetpackcompose.screen.FavoritesScreen
import com.example.jetpackcompose.screen.HomeScreen


@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {

        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController)}
        composable(route = MovieScreens.DetailScreen.name + "/{movieId}", arguments = listOf(navArgument("movieId") {
            type = NavType.StringType
        })
        ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"))}

        composable(route = MovieScreens.FavoritesScreen.name) { FavoritesScreen(navController = navController)}
    }
}