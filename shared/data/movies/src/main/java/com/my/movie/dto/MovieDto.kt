package com.my.movie.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName(value = "poster_path")
    val posterPath: String?,
    val adult: Boolean?,
    val overview: String?,
    @SerializedName(value = "release_date")
    val releaseDate: String?,
    @SerializedName(value = " genre_ids")
    val genreIds: List<Int>?,
    val id: Int?,
    @SerializedName(value = "original_title")
    val originalTitle: String?,
    @SerializedName(value = "original_language")
    val originalLanguage: String?,
    val title: String?,
    @SerializedName(value = "backdrop_path")
    val backdropPath: String?,
    val popularity: Float?,
    @SerializedName(value = "vote_count")
    val voteCount: Int?,
    val video: Boolean?,
    @SerializedName(value = "vote_average")
    val voteAverage: Float?
)