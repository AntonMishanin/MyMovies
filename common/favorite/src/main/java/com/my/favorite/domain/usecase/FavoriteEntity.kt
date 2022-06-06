package com.my.favorite.domain.usecase

data class FavoriteEntity(
    val id: Int,
    val posterPath: String,
    val title: String,
    val overview: String,
    val rating: Float,
    val studio: String,
    val genre: String,
    val year: String
)