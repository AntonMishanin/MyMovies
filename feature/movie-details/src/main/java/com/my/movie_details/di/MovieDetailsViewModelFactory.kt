package com.my.movie_details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.movie.MovieRepository
import com.my.movie.favorite.FavoriteRepository
import com.my.movie_details.presentation.MovieDetailsViewModel

class MovieDetailsViewModelFactory(
    private val id: String,
    private val favoriteRepository: FavoriteRepository,
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(id, favoriteRepository, movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}