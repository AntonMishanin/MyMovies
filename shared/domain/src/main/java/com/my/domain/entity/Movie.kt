package com.my.domain.entity

data class Movie(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterPath: String
) {
    val rating: Float
        get() = voteAverage.toFloat() / 2
}
