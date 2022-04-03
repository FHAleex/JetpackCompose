package com.example.jetpackcompose.screen

import android.view.Gravity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.models.Movie
import com.example.jetpackcompose.models.getMovies
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.widgets.HorizontalScrollableImageView
import com.example.jetpackcompose.widgets.MovieRow


@Composable
fun DetailScreen(navController: NavController = rememberNavController(), movieId: String?) {

    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp ) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()    // go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = movie.title, style = MaterialTheme.typography.h6)
                }

            }
        }
    ) {
        MainContent(movie = movie)
    }

}

@Composable
fun MainContent(movie: Movie) {

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        Column {
            MovieRow(movie = movie)

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            Text(text = "Movie Images", style = MaterialTheme.typography.h5, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

            HorizontalScrollableImageView(movie = movie)

        }
    }


}


fun filterMovie(movieId: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}