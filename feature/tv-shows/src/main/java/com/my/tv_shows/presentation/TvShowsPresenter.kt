package com.my.tv_shows.presentation

import com.my.tv_shows.base.BasePresenter
import com.my.tv_shows.data.TvShowsRepository
import com.my.tv_shows.entity.TvShowsEntity

class TvShowsPresenter(private val repository: TvShowsRepository) : BasePresenter<TvShowsView>() {

    override fun onViewReady() {
        val content = repository.fetchTvShows()
        view?.setTvShowsList(content)
    }

    fun onItemTvShowClicked(tvShowsEntity: TvShowsEntity) {
    }
}