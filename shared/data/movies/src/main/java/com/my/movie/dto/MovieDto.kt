package com.my.movie.dto

import com.google.gson.annotations.SerializedName
import com.my.domain.entity.Movie

data class MovieDto(
    @SerializedName(value = "poster_path")
    private val posterPath: String?,
    private val adult: Boolean?,
    private val overview: String?,
    @SerializedName(value = "release_date")
    private val releaseDate: String?,
    @SerializedName(value = " genre_ids")
    private val genreIds: List<Int>?,
    private val id: Int?,
    @SerializedName(value = "original_title")
    private val originalTitle: String?,
    @SerializedName(value = "original_language")
    private val originalLanguage: String?,
    private val title: String?,
    @SerializedName(value = "backdrop_path")
    private val backdropPath: String?,
    private val popularity: Float?,
    @SerializedName(value = "vote_count")
    private val voteCount: Int?,
    private val video: Boolean?,
    @SerializedName(value = "vote_average")
    private val voteAverage: Float?
) {
    fun toValueObject(): Movie = Movie(
        title = title ?: "",
        voteAverage = voteAverage?.toDouble() ?: 5.0
    )
}