package com.my.movie.di

import android.content.Context
import com.my.movie.MoviesApi
import com.my.movie.MoviesDataSource
import com.my.movie.MovieRepository
import com.my.movie.storage.MovieDatabase
import com.my.remote.RemoteFactory

class MoviesFactory {
    fun provideMovieRepository(context: Context): MovieRepository {
        val retrofit = RemoteFactory().provideRetrofit()
        val movieApi = retrofit.create(MoviesApi::class.java)
        val movieDataSource = MoviesDataSource(movieApi)

        val movieDao = MovieDatabase.getFavoriteDatabase(context).getMovieDao()
        return MovieRepository(movieDataSource, movieDao)
    }
}