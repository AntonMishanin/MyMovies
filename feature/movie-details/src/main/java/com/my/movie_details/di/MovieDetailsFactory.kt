package com.my.movie_details.di

import android.content.Context
import android.os.Bundle
import com.my.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.domain.usecase.FetchMovieByIdUseCase
import com.my.domain.usecase.IsFavoriteByIdUseCase
import com.my.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movie.di.MoviesFactory
import com.my.movie.favorite.di.FavoriteFactory

class MovieDetailsFactory {
    fun provideViewModelFactory(
        arguments: Bundle?,
        context: Context
    ): MovieDetailsViewModelFactory {
        val id = arguments?.getString("id") ?: throw IllegalArgumentException("id must not be null")
        val favoriteRepository = FavoriteFactory().provideRepository(context)
        val movieRepository = MoviesFactory().provideMovieRepository(context)
        val isFavoriteByIdUseCase = IsFavoriteByIdUseCase(favoriteRepository)
        val saveMovieToFavoriteUseCase = SaveMovieToFavoriteUseCase(favoriteRepository)
        val deleteFromFavoriteByIdUseCase = DeleteFromFavoriteByIdUseCase(favoriteRepository)
        val fetchMovieByIdUseCase = FetchMovieByIdUseCase(movieRepository)
        return MovieDetailsViewModelFactory(
            id,
            isFavoriteByIdUseCase,
            saveMovieToFavoriteUseCase,
            deleteFromFavoriteByIdUseCase,
            fetchMovieByIdUseCase
        )
    }
}