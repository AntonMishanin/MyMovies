package com.my.movie_details.di

import android.os.Bundle
import com.my.movie_details.data.MovieDetailsRepository
import java.lang.IllegalArgumentException

class MovieDetailsFactory {
    fun provideViewModelFactory(arguments: Bundle?): MovieDetailsViewModelFactory {
        val repository = MovieDetailsRepository()
        val id = arguments?.getString("id") ?: throw IllegalArgumentException("id must not be null")
        return MovieDetailsViewModelFactory(id, repository)
    }
}