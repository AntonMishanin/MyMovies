package com.my.movies.detail.di

import android.content.Context
import android.os.Bundle
import com.my.core.di.Feature
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movies.data.di.MoviesFactory
import com.my.movies.detail.presentation.MovieDetailsViewModel
import com.my.movies.domain.FetchMovieByIdUseCase
import com.my.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides

@Module
internal class MovieDetailsModule {

    @Feature
    @Provides
    internal fun provideMovieDetailsViewModel(
        id: String,
        isFavoriteByIdUseCase: IsFavoriteByIdUseCase,
        saveMovieToFavoriteUseCase: SaveMovieToFavoriteUseCase,
        deleteFromFavoriteByIdUseCase: DeleteFromFavoriteByIdUseCase,
        fetchMovieByIdUseCase: FetchMovieByIdUseCase
    ) = MovieDetailsViewModel(
        id,
        isFavoriteByIdUseCase,
        saveMovieToFavoriteUseCase,
        deleteFromFavoriteByIdUseCase,
        fetchMovieByIdUseCase
    )

    @Provides
    internal fun provideId(arguments: Bundle?) =
        arguments?.getString("id") ?: throw IllegalArgumentException("id must be not null")

    @Provides
    internal fun provideMoviesRepository(context: Context) =
        MoviesFactory().provideMovieRepository(context)

    @Provides
    internal fun provideFetchMovieByIdUseCase(moviesRepository: MovieRepository) =
        FetchMovieByIdUseCase(moviesRepository)
}