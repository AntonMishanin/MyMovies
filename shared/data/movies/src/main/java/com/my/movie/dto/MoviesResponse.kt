package com.my.movie.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int?,
    val results: List<MovieDto>?,
    val maximum: String?,
    val minimum: String?,
    @SerializedName(value = "total_pages")
    val totalPages: Int?,
    @SerializedName(value = "total_results")
    val totalResults: Int?
)