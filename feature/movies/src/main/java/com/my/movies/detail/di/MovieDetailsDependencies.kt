package com.my.movies.detail.di

import android.content.Context
import com.my.core.di.FeatureDependencies
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase

interface MovieDetailsDependencies : FeatureDependencies {

    fun context(): Context

    fun provideIsFavoriteByIdUseCase(): IsFavoriteByIdUseCase

    fun provideSaveMovieToFavoriteUseCase(): SaveMovieToFavoriteUseCase

    fun provideDeleteFromFavoriteByIdUseCase(): DeleteFromFavoriteByIdUseCase
}