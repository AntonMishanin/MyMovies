package com.my.favorite.data

import com.my.core.converter.Converter
import com.my.favorite.data.dto.FavoriteDbo
import com.my.favorite.domain.usecase.FavoriteEntity

internal class FavoriteToDboConverter : Converter<FavoriteEntity, FavoriteDbo> {

    override fun convert(input: FavoriteEntity) = FavoriteDbo(
        id = input.id,
        title = input.title,
        posterPath = input.posterPath,
        rating = input.rating
    )
}