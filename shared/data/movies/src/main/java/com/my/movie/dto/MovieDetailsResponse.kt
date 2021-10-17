package com.my.movie.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val adult: Boolean?,
    @SerializedName(value = "backdrop_path")
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<GenreDto>?,
    val homepage: String?,
    val id: Int?,
    @SerializedName(value = "imdb_id")
    val imdbId: String?,
    @SerializedName(value = "original_language")
    val originalLanguage: String?,
    @SerializedName(value = "original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float?,
    @SerializedName(value = "poster_path")
    val posterPath: String?,
    @SerializedName(value = "production_companies")
    val productionCompanies: List<ProductionCompanyDto>?,
    @SerializedName(value = "production_countries")
    val productionCountries: List<ProductionCountryDto>?,
    @SerializedName(value = "release_date")
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Int?,
    @SerializedName(value = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName(value = "vote_average")
    val voteAverage: Float?,
    @SerializedName(value = "vote_count")
    val voteCount: Int?
)
