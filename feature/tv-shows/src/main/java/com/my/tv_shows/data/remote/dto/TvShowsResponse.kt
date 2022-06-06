package com.my.tv_shows.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class TvShowsResponse(
    val page: Int?,
    val results: List<TvShowDto>?,
    @SerializedName(value = "total_results")
    val totalResults: Int?,
    @SerializedName(value = "total_pages")
    val totalPages: Int?
)
