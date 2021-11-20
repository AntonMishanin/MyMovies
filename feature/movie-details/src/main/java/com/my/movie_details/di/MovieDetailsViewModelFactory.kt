package com.my.movie_details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.domain.usecase.FetchMovieByIdUseCase
import com.my.domain.usecase.IsFavoriteByIdUseCase
import com.my.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movie_details.presentation.MovieDetailsViewModel

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