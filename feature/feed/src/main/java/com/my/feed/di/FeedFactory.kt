package com.my.feed.di

import com.my.movie.di.MoviesFactory

class FeedFactory {
    fun provideViewModelFactory(): FeedViewModelFactory {
        val movieRepository = MoviesFactory().provideMovieRepository()
        return FeedViewModelFactory(movieRepository)
    }
}