package com.my.favorite.data

import com.my.core.converter.IterableConverter
import com.my.favorite.data.dto.FavoriteDbo
import com.my.favorite.domain.usecase.FavoriteEntity

internal class FavoriteToDomainConverter : IterableConverter<FavoriteDbo, FavoriteEntity>() {

    override fun convert(input: FavoriteDbo) = FavoriteEntity(
        id = input.id,
        title = input.title,
        overview = "this.overview",
        posterPath = input.posterPath,
        rating = input.rating,
        studio = "",
        genre = "",
        year = ""
    )
}