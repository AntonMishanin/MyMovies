package com.my.movie.favorite

import com.my.domain.entity.MovieDetails
import com.my.movie.favorite.dto.FavoriteEntity

internal fun MovieDetails.toFavorite(): FavoriteEntity = FavoriteEntity(
    id = this.id,
    title = this.title,
    posterPath = this.posterPath,
    rating = this.rating
)

internal fun FavoriteEntity.toMovieDetails() = MovieDetails(
    id = this.id,
    title = this.title,
    posterPath = this.posterPath,
    rating = this.rating
)