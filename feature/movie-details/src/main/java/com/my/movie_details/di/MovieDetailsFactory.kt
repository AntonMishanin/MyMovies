package com.my.movie_details.di

import android.content.Context
import android.os.Bundle
import com.my.movie.di.MoviesFactory
import com.my.movie.favorite.di.FavoriteFactory
import java.lang.IllegalArgumentException

class MovieDetailsFactory {
    fun provideViewModelFactory(
        arguments: Bundle?,
        context: Context
    ): MovieDetailsViewModelFactory {
        val id = arguments?.getString("id") ?: throw IllegalArgumentException("id must not be null")
        val favoriteRepository = FavoriteFactory().provideRepository(context)
        val movieRepository = MoviesFactory().provideMovieRepository()
        return MovieDetailsViewModelFactory(id, favoriteRepository, movieRepository)
    }
}