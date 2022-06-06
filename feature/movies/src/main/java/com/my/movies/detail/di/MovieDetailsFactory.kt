package com.my.movies.detail.di

import android.content.Context
import android.os.Bundle
import com.my.favorite.data.di.FavoriteFactory
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movies.data.di.MoviesFactory
import com.my.movies.domain.FetchMovieByIdUseCase

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