package com.my.movie.di

import com.my.movie.MoviesApi
import com.my.movie.MoviesDataSource
import com.my.movie.MovieRepository
import com.my.remote.RemoteFactory

class MoviesFactory {
    fun provideMovieRepository(): MovieRepository {
        val retrofit = RemoteFactory().provideRetrofit()
        val movieApi = retrofit.create(MoviesApi::class.java)
        val movieDataSource = MoviesDataSource(movieApi)
        return MovieRepository(movieDataSource)
    }
}