package com.my.movies.data.di

import android.content.Context
import com.my.core.data.RemoteFactory
import com.my.movies.data.MovieRepositoryImpl
import com.my.movies.data.MoviesApi
import com.my.movies.data.MoviesDataSource
import com.my.movies.data.storage.MovieDatabase
import com.my.movies.domain.MovieRepository

class MoviesFactory {
    fun provideMovieRepository(context: Context): MovieRepository {
        val retrofit = RemoteFactory().provideRetrofit()
        val movieApi = retrofit.create(MoviesApi::class.java)
        val movieDataSource = MoviesDataSource(movieApi)

        val movieDao = MovieDatabase.getFavoriteDatabase(context).getMovieDao()
        return MovieRepositoryImpl(movieDataSource, movieDao)
    }
}