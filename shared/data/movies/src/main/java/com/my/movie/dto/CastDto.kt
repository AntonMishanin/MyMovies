package com.my.movie.dto

import com.google.gson.annotations.SerializedName

data class CastDto(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    @SerializedName(value = "known_for_department")
    val knownForDepartment: String?,
    val name: String?,
    @SerializedName(value = "original_name")
    val originalName: String?,
    val popularity: Float?,
    @SerializedName(value = "profile_path")
    val profilePath: String?,
    @SerializedName(value = "cast_id")
    val castId: Int?,
    val character: String?,
    @SerializedName(value = "credit_id")
    val creditId: String?,
    val order: Int?
)