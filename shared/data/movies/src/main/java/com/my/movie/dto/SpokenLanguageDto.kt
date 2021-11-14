package com.my.movie.dto

import com.google.gson.annotations.SerializedName

internal data class SpokenLanguageDto(
    @SerializedName(value = "iso_3166_1")
    val iso_639_1: String?,
    val name: String?
)
