package com.my.tv_shows.presentation

import com.my.core.mvp.BaseView
import com.xwray.groupie.viewbinding.BindableItem

interface TvShowsView : BaseView {

    fun showState(state: List<BindableItem<*>>)
}