package com.my.movie_details.data

import com.my.movie_details.data.source.actors.ActorDto
import com.my.movie_details.data.source.movies.MovieDto
import com.my.movie_details.entity.MovieEntity

fun MovieDto.toMovieEntity(
    actorsDto: List<ActorDto>,
    studiosDto: List<String>,
    genresDto: List<String>
) = MovieEntity(
    id = this.id,
    previewId = this.previewId,
    title = this.title,
    rating = this.rating,
    isFavorite = this.isFavorite,
    descriptionFull = this.descriptionFull,
    actorsList = actorsDto.map { it.toActor() },
    studio = studiosDto.toString(),
    genre = genresDto.toString(),
    year = this.year.toString()
)

private fun ActorDto.toActor() = MovieEntity.Actor(
    id = this.id,
    name = this.name,
    previewId = this.previewId
)