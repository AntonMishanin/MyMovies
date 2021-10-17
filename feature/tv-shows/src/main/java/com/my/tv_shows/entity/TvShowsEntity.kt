package com.my.tv_shows.entity

import androidx.annotation.DrawableRes

data class TvShowsEntity(
    val id: String,
    @DrawableRes
    val imagePath: Int,
    val title: String,
    val rating: Float
)