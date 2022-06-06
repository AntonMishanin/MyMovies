package com.my.favorite.data

import com.my.favorite.data.dto.FavoriteDbo
import com.my.favorite.domain.usecase.FavoriteEntity

internal fun FavoriteEntity.toDbo() = FavoriteDbo(
    id = this.id,
    title = this.title,
    posterPath = this.posterPath,
    rating = this.rating
)

internal fun FavoriteDbo.toDomain() = FavoriteEntity(
    id = this.id,
    title = this.title,
    overview = "this.overview",
    posterPath = this.posterPath,
    rating = this.rating,
    studio = "",
    genre = "",
    year = ""
)