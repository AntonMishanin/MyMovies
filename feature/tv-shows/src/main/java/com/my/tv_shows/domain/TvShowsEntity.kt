package com.my.tv_shows.domain

internal data class TvShowsEntity(
    val id: Int,
    val imagePath: String,
    val title: String,
    val rating: Float
)