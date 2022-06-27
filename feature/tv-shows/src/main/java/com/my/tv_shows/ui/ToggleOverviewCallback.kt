package com.my.tv_shows.ui

import com.my.tv_shows.domain.TvShowsEntity

internal interface ToggleOverviewCallback {

    fun onToggleOverviewClicked(tvShowsEntity: TvShowsEntity)
}