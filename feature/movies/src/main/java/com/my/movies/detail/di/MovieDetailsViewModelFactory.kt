package com.my.movies.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movies.detail.presentation.MovieDetailsViewModel
import com.my.movies.domain.FetchMovieByIdUseCase

class MovieDetailsViewModelFactory(
    private val id: String,
    private val isFavoriteByIdUseCase: IsFavoriteByIdUseCase,
    private val saveMovieToFavoriteUseCase: SaveMovieToFavoriteUseCase,
    private val deleteFromFavoriteByIdUseCase: DeleteFromFavoriteByIdUseCase,
    private val fetchMovieByIdUseCase: FetchMovieByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(
                id,
                isFavoriteByIdUseCase,
                saveMovieToFavoriteUseCase,
                deleteFromFavoriteByIdUseCase,
                fetchMovieByIdUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}