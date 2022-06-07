package com.my.movies.data.di

import android.content.Context
import com.my.core.data.RemoteFactory
import com.my.movies.data.MovieRepositoryImpl
import com.my.movies.data.MoviesApi
import com.my.movies.data.MoviesDataSource
import com.my.movies.data.storage.MovieDao
import com.my.movies.data.storage.MovieDatabase
import com.my.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class MoviesDataModule {

    @Provides
    internal fun provideRetrofit() = RemoteFactory().provideRetrofit()

    @Provides
    internal fun provideMoviesApi(retrofit: Retrofit) = retrofit.create(MoviesApi::class.java)

    @Provides
    internal fun provideMoviesDataSource(moviesApi: MoviesApi) = MoviesDataSource(moviesApi)

    @Provides
    internal fun provideMoviesDao(context: Context) =
        MovieDatabase.getFavoriteDatabase(context).getMovieDao()

    @Provides
    internal fun provideMoviesRepositoryImpl(
        moviesDataSource: MoviesDataSource,
        moviesDao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(moviesDataSource, moviesDao)
}