package com.my.movie.storage.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class UpcomingEntity(
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    @PrimaryKey
    val id: Int,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val popularity: Float,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Float
)