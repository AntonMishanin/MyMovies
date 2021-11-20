package com.my.movie.dto

import com.google.gson.annotations.SerializedName

internal data class CrewDto(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    @SerializedName(value = "known_for_department")
    val knownForDepartment: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Float?,
    @SerializedName(value = "profile_path")
    val profilePath: String?,
    @SerializedName(value = "credit_id")
    val creditId: String?,
    val department: String?,
    val job: String?
)