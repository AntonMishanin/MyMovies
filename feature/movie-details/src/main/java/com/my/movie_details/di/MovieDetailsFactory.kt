package com.my.movie_details.di

import android.os.Bundle
import com.my.movie_details.data.MovieDetailsRepository
import com.my.movie_details.data.source.actors.ActorsDataSource
import com.my.movie_details.data.source.genre.GenresDataSource
import com.my.movie_details.data.source.movies.MoviesDataSource
import com.my.movie_details.data.source.studios.StudiosDataSource
import java.lang.IllegalArgumentException

class MovieDetailsFactory {
    fun provideViewModelFactory(arguments: Bundle?): MovieDetailsViewModelFactory {
        val repository = MovieDetailsRepository(
            ActorsDataSource(),
            GenresDataSource(),
            MoviesDataSource(),
            StudiosDataSource()
        )
        val id = arguments?.getString("id") ?: throw IllegalArgumentException("id must not be null")
        return MovieDetailsViewModelFactory(id, repository)
    }
}