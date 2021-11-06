package com.my.feed.di

import android.content.Context
import com.my.movie.di.MoviesFactory

class FeedFactory {
    fun provideViewModelFactory(context: Context): FeedViewModelFactory {
        val movieRepository = MoviesFactory().provideMovieRepository(context)
        return FeedViewModelFactory(movieRepository)
    }
}