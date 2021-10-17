package com.my.movie.dto

import com.google.gson.annotations.SerializedName
import com.my.domain.entity.Movie

data class MoviesResponse(
    private val page: Int?,
    private val results: List<MovieDto>?,
    private val maximum: String?,
    private val minimum: String?,
    @SerializedName(value = "total_pages")
    private val totalPages: Int?,
    @SerializedName(value = "total_results")
    private val totalResults: Int?
) {
    fun toValueObject(): List<Movie>? = results?.map { movieDto -> movieDto.toValueObject() }
}