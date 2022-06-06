package com.my.tv_shows.di

import com.my.tv_shows.data.di.TvFactory
import com.my.tv_shows.domain.FetchPopularTvShowsUseCase
import com.my.tv_shows.presentation.TvShowsPresenter

class TvShowsFactory {
    fun providePresenter(): TvShowsPresenter {
        val repository = TvFactory().provideRepository()
        val fetchPopularTvShowsUseCase = FetchPopularTvShowsUseCase(repository)
        return TvShowsPresenter(fetchPopularTvShowsUseCase)
    }
}