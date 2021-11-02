package com.my.tv.remote.dto

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    val page: Int?,
    val results: List<TvShowDto>?,
    @SerializedName(value = "total_results")
    val totalResults: Int?,
    @SerializedName(value = "total_pages")
    val totalPages: Int?
)
