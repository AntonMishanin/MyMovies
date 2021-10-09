package com.my.movie_details.entity

import androidx.annotation.DrawableRes

data class MovieEntity(
    val id: String,
    @DrawableRes
    val previewId: Int,
    val title: String,
    val rating: Float,
    val isFavorite: Boolean,
    val descriptionShort: String,
    val descriptionFull: String,
    val actorsList: List<Actor>,
    val studio: String,
    val genre: String,
    val year: String
) {
    data class Actor(
        val id: String,
        val name: String,
        val previewPath: String
    )
}