package com.my.tv_shows.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class TvShowsDbo(
    @PrimaryKey
    val id: Int,
    val type: String,
    val posterPath: String,
    val popularity: Float,
    val backdropPath: String,
    val voteAverage: Float,
    val overview: String,
    val firstAirDate: String,
    val originalLanguage: String,
    val voteCount: Int,
    val name: String,
    val originalName: String
)