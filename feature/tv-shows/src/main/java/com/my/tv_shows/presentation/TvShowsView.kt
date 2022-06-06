package com.my.tv_shows.presentation

import com.my.core.mvp.BaseView
import com.my.tv_shows.domain.TvShowsEntity

interface TvShowsView : BaseView {
    fun setTvShowsList(content: List<TvShowsEntity>)
}