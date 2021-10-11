package com.my.tv_shows.presentation

import com.my.tv_shows.data.TvShowsRepository
import com.my.tv_shows.entity.TvShowsEntity

class TvShowsPresenter(private val repository: TvShowsRepository) {

    private var view: TvShowsView? = null

    fun onViewAttached(view: TvShowsView) {
        this.view = view

        val content = repository.fetchTvShows()
        view.setTvShowsList(content)
    }

    fun onViewDetached() {
        view = null
    }

    fun onItemTvShowClicked(tvShowsEntity: TvShowsEntity) {

    }
}