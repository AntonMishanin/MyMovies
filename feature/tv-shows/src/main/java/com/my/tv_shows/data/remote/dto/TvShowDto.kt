package com.my.tv_shows.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class TvShowDto(
    @SerializedName(value = "poster_path")
    val posterPath: String?,
    val popularity: Float?,
    val id: Int?,
    @SerializedName(value = "backdrop_path")
    val backdropPath: String?,
    @SerializedName(value = "vote_average")
    val voteAverage: Float?,
    val overview: String?,
    @SerializedName(value = "first_air_date")
    val firstAirDate: String?,
    @SerializedName(value = "origin_country")
    val originCountry: List<String>?,
    @SerializedName(value = "genre_ids")
    val genreIds: List<Int>?,
    @SerializedName(value = "original_language")
    val originalLanguage: String?,
    @SerializedName(value = "vote_count")
    val voteCount: Int?,
    val name: String?,
    @SerializedName(value = "original_name")
    val originalName: String?
)
