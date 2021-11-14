package com.my.movie.dto

import com.google.gson.annotations.SerializedName

internal data class ProductionCompanyDto(
    val id: Int?,
    @SerializedName(value = "logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName(value = "origin_country")
    val originCountry: String?
)