package com.my.movies.data.dto

internal data class CreditsResponse(
    val id: Int?,
    val cast: List<CastDto>?,
    val crew: List<CrewDto>?
)