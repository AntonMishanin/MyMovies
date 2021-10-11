package com.my.movie_details.data.source.actors

import androidx.annotation.DrawableRes

data class ActorDto(
    val id: String,
    val name: String,
    @DrawableRes
    val previewId: Int
)