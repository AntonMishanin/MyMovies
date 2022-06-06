package com.my.tv_shows.domain

data class TvShowsEntity(
    val id: Int,
    val imagePath: String,
    val title: String,
    val rating: Float
)