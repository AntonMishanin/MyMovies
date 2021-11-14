package com.my.domain.entity

data class CompositeMovieEntity(
    val nowPlaying: List<Movie>,
    val popular: List<Movie>,
    val upcoming: List<Movie>
)