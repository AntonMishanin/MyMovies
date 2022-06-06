package com.my.tv_shows.presentation

import com.my.domain.entity.TvShowsEntity
import com.my.resources.mvp.BaseView

interface TvShowsView : BaseView {
    fun setTvShowsList(content: List<TvShowsEntity>)
}