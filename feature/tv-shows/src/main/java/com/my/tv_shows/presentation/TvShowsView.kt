package com.my.tv_shows.presentation

import com.my.tv_shows.base.BaseView
import com.my.tv_shows.entity.TvShowsEntity

interface TvShowsView : BaseView {
    fun setTvShowsList(content: List<TvShowsEntity>)
}