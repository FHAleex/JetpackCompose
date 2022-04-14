package com.example.jetpackcompose.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.models.Movie
import java.nio.file.Files.exists

class FavoritesViewModel : ViewModel() {

    private val _favoriteMovies = mutableStateListOf<Movie>()
    val favoriteMovies: List<Movie>
        get() = _favoriteMovies


    fun addFavorite(movie: Movie) {
        _favoriteMovies.add(movie)

    }

    fun removeFavorite(movie: Movie) {
        _favoriteMovies.remove(movie)
    }

  /*  fun getAllFavorite(): List<Movie> {
        return _favoriteMovies
    }*/

    fun checkFavorite(movie: Movie): Boolean {
        return _favoriteMovies.contains(movie)
    }
}