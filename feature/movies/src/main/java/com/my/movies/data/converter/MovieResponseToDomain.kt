package com.my.movies.data.converter

import com.my.core.converter.Converter
import com.my.movies.data.dto.MovieDetailsResponse
import com.my.movies.domain.MovieDetails

internal class MovieResponseToDomain : Converter<MovieDetailsResponse, MovieDetails> {

    override fun convert(input: MovieDetailsResponse) = MovieDetails(
        id = input.id ?: throw NullPointerException("id must not be null"),
        posterPath = "https://image.tmdb.org/t/p/w500" + input.posterPath,// TODO: use BuildConfigWrapper
        title = input.title ?: "",
        overview = input.overview ?: "",
        rating = input.voteAverage ?: 5f,
        studio = input.productionCompanies.toString(),
        genre = input.genres.toString(),
        year = input.releaseDate ?: ""
    )
}