package com.my.movie.favorite.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String,
    val rating: Float
)