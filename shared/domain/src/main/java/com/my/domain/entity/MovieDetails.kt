package com.my.domain.entity

data class MovieDetails(
    val id: Int,
    val posterPath: String,
    val title: String,
    val overview: String,
    val rating: Float,
    val studio: String,
    val genre: String,
    val year: String
)