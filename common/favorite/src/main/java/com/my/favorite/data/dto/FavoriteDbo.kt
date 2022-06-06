package com.my.favorite.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class FavoriteDbo(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String,
    val rating: Float
)