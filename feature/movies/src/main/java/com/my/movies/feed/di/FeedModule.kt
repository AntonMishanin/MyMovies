package com.my.movies.feed.di

import com.my.movies.domain.FetchCompositeMovieUseCase
import com.my.movies.domain.MovieRepository
import com.my.movies.feed.FeedViewModel
import dagger.Module
import dagger.Provides

@Module
class FeedModule {

    @Provides
    internal fun provideFeedViewModel(fetchCompositeMovieUseCase: FetchCompositeMovieUseCase) =
        FeedViewModel(fetchCompositeMovieUseCase)

    @Provides
    fun provideFetchCompositeMovieUseCase(movieRepository: MovieRepository) =
        FetchCompositeMovieUseCase(movieRepository)
}