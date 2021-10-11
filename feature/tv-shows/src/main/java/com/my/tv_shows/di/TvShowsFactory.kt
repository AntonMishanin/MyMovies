package com.my.tv_shows.di

import com.my.tv_shows.data.TvShowsDataSource
import com.my.tv_shows.data.TvShowsRepository
import com.my.tv_shows.presentation.TvShowsPresenter

class TvShowsFactory {
    fun providePresenter(): TvShowsPresenter {
        val dataSource = TvShowsDataSource()
        val repository = TvShowsRepository(dataSource)
        return TvShowsPresenter(repository)
    }
}