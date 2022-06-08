package com.my.movies.detail.di

import android.os.Bundle
import com.my.core.di.Feature
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movies.detail.presentation.MovieDetailsToDomainConverter
import com.my.movies.detail.presentation.MovieDetailsViewModel
import com.my.movies.domain.FetchMovieByIdUseCase
import com.my.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides

@Module
internal class MovieDetailsModule {

    @Feature
    @Provides
    fun provideMovieDetailsViewModel(
        id: String,
        isFavoriteByIdUseCase: IsFavoriteByIdUseCase,
        saveMovieToFavoriteUseCase: SaveMovieToFavoriteUseCase,
        deleteFromFavoriteByIdUseCase: DeleteFromFavoriteByIdUseCase,
        fetchMovieByIdUseCase: FetchMovieByIdUseCase,
        movieDetailsToDomainConverter: MovieDetailsToDomainConverter
    ) = MovieDetailsViewModel(
        id,
        isFavoriteByIdUseCase,
        saveMovieToFavoriteUseCase,
        deleteFromFavoriteByIdUseCase,
        fetchMovieByIdUseCase,
        movieDetailsToDomainConverter
    )

    @Provides
    fun provideId(arguments: Bundle?) =
        arguments?.getString(MOVIE_ID_KEY) ?: throw IllegalArgumentException("id must be not null")

    @Provides
    fun provideFetchMovieByIdUseCase(moviesRepository: MovieRepository) =
        FetchMovieByIdUseCase(moviesRepository)

    @Provides
    fun provideMovieDetailsToDomainConverter() = MovieDetailsToDomainConverter()

    private companion object {
        const val MOVIE_ID_KEY = "id"
    }
}