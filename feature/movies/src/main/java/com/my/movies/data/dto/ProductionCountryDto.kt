package com.my.movies.data.dto

import com.google.gson.annotations.SerializedName

internal data class ProductionCountryDto(
    @SerializedName(value = "iso_3166_1")
    val iso_3166_1: String?,
    val name: String?
)
