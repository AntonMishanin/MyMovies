package com.my.tv_shows.presentation

import com.my.tv_shows.entity.TvShowsEntity

interface TvShowsView {
    fun setTvShowsList(content: List<TvShowsEntity>)
}