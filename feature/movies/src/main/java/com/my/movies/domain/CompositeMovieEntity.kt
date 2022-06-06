package com.my.movies.domain

data class CompositeMovieEntity(
    val nowPlaying: List<Movie>,
    val popular: List<Movie>,
    val upcoming: List<Movie>
)