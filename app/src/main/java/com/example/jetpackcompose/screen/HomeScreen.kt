package com.example.jetpackcompose.screen

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.models.Movie
import com.example.jetpackcompose.models.getMovies
import com.example.jetpackcompose.navigation.MovieScreens
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.viewmodels.FavoritesViewModel
import com.example.jetpackcompose.widgets.FavoriteIcon
import com.example.jetpackcompose.widgets.MovieRow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: FavoritesViewModel = viewModel()) {


    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = MovieScreens.FavoritesScreen.name) }) {
                            Row() {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "my favorites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )

                            }
                        }
                    }
                }
            )
        }
    ) {
        MainContent(navController = navController, viewModel = viewModel)
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(navController: NavController, movieList: List<Movie> = getMovies(), viewModel: FavoritesViewModel) {

    LazyColumn {
        items(movieList) { name ->
            Row {
                MovieRow(name,
                    onItemClick = { movieId ->
                        //Log.d("MainContent","My callback value: $movieId")
                        navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                    },
                    content = {
                        FavoriteIcon(movie = name, isfavorite = viewModel.checkFavorite(name),
                            onFavClick = { favmovie ->
                                if (!viewModel.checkFavorite(favmovie)) {
                                    viewModel.addFavorite(favmovie)

                                } else {
                                    viewModel.removeFavorite(favmovie)

                                }

                            })
                    })
            }
        }
    }
}