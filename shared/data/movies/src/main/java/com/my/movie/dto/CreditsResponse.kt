package com.my.movie.dto

internal data class CreditsResponse(
    val id: Int?,
    val cast: List<CastDto>?,
    val crew: List<CrewDto>?
)