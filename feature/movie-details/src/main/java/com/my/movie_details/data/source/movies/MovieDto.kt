package com.my.movie_details.data.source.movies

import androidx.annotation.DrawableRes

data class MovieDto(
    val id: String,
    @DrawableRes
    val previewId: Int,
    val title: String,
    val rating: Float,
    val isFavorite: Boolean,
    val descriptionFull: String,
    val actorsIdList: List<String>,
    val studiosIdList: List<String>,
    val genresIdList: List<String>,
    val year: Int
)