package com.my.movies.detail.presentation

import com.my.core.converter.Converter
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.movies.domain.MovieDetails

class MovieDetailsToDomainConverter : Converter<MovieDetails, FavoriteEntity> {

    override fun convert(input: MovieDetails) = FavoriteEntity(
        id = input.id,
        title = input.title,
        overview = "this.overview",
        posterPath = input.posterPath,
        rating = input.rating,
        studio = input.studio,
        genre = input.genre,
        year = input.year
    )
}