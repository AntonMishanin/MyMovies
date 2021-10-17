package com.my.domain.entity

data class Movie(
    val title: String,
    val voteAverage: Double
) {
    val rating: Float
        get() = voteAverage.toFloat() / 2
}
