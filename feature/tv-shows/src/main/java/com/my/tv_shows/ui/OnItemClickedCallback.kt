package com.my.tv_shows.ui

import com.my.tv_shows.domain.TvShowsEntity

internal interface OnItemClickedCallback {

    fun onItemClicked(tvShowsEntity: TvShowsEntity)
}