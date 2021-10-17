package com.my.movie_details.data

import com.my.movie_details.data.source.actors.ActorsDataSource
import com.my.movie_details.data.source.genre.GenresDataSource
import com.my.movie_details.data.source.movies.MoviesDataSource
import com.my.movie_details.data.source.studios.StudiosDataSource
import com.my.movie_details.entity.MovieEntity

class MovieDetailsRepository(
    private val actorsDataSource: ActorsDataSource,
    private val genresDataSource: GenresDataSource,
    private val moviesDataSource: MoviesDataSource,
    private val studiosDataSource: StudiosDataSource
) {
    fun getMovieById(id: String): MovieEntity {
        val movieDto = moviesDataSource.fetchMovieById(id)
        val actors = actorsDataSource.fetchActorsById(movieDto.actorsIdList)
        val studios = studiosDataSource.fetchStudiosById(movieDto.studiosIdList)
        val genresDataSource = genresDataSource.fetchGenresById(movieDto.genresIdList)
        return movieDto.toMovieEntity(actors, studios, genresDataSource)
    }
}