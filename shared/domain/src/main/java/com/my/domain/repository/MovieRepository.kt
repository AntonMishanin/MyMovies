package com.my.domain.repository

import com.my.domain.entity.Movie
import com.my.domain.entity.MovieDetails
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieRepository {
    fun fetchNowPlayingFromStorage(): Flowable<List<Movie>>

    fun fetchNowPlayingFromNetwork(): Single<List<Movie>>

    fun saveNowPlayingToStorage(movies: List<Movie>): Completable

    fun fetchUpcomingFromStorage(): Flowable<List<Movie>?>

    fun fetchUpcomingFromNetwork(): Single<List<Movie>?>

    fun saveUpcomingToStorage(movies: List<Movie>): Completable

    fun fetchPopularFromStorage(): Flowable<List<Movie>?>

    fun fetchPopularFromNetwork(): Single<List<Movie>?>

    fun savePopularToStorage(movies: List<Movie>): Completable

    fun fetchMovieByIdFromStorage(id: String): Single<MovieDetails>

    fun fetchMovieByIdFromNetwork(id: String): Single<MovieDetails>

    fun saveMovieByIdToStorage(id: String): Completable
}