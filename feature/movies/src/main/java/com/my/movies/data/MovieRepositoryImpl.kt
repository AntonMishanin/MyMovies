package com.my.movies.data

import com.my.movies.data.converter.*
import com.my.movies.data.storage.MovieDao
import com.my.movies.domain.Movie
import com.my.movies.domain.MovieDetails
import com.my.movies.domain.MovieRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

internal class MovieRepositoryImpl(
    private val remoteDataSource: MoviesDataSource,
    private val storage: MovieDao,
    private val popularToDboConverter: PopularToDboConverter,
    private val upcomingToDboConverter: UpcomingToDboConverter,
    private val nowPlayingToDboConverter: NowPlayingToDboConverter,
    private val upcomingToDomainConverter: UpcomingToDomainConverter,
    private val popularToDomainConverter: PopularToDomainConverter,
    private val nowPlayingToDomainConverter: NowPlayingToDomainConverter,
    private val movieResponseToDomain: MovieResponseToDomain
) : MovieRepository {

    override fun fetchNowPlayingFromStorage(): Flowable<List<Movie>> =
        storage.loadAllNowPlaying().map(nowPlayingToDomainConverter::convert)

    override fun fetchNowPlayingFromNetwork(): Single<List<Movie>> =
        remoteDataSource.fetchNowPlaying().map { it.toValueObject() }

    override fun saveNowPlayingToStorage(movies: List<Movie>): Completable =
        storage.insertNowPlaying(nowPlayingToDboConverter.convert(movies))

    override fun fetchUpcomingFromStorage(): Flowable<List<Movie>?> =
        storage.loadAllUpcoming().map(upcomingToDomainConverter::convert)

    override fun fetchUpcomingFromNetwork(): Single<List<Movie>?> =
        remoteDataSource.fetchUpcoming().map { it.toValueObject() }

    override fun saveUpcomingToStorage(movies: List<Movie>): Completable =
        storage.insertUpcoming(upcomingToDboConverter.convert(movies))

    override fun fetchPopularFromStorage(): Flowable<List<Movie>?> =
        storage.loadAllPopular().map(popularToDomainConverter::convert)

    override fun fetchPopularFromNetwork(): Single<List<Movie>?> =
        remoteDataSource.fetchPopular().map { it.toValueObject() }

    override fun savePopularToStorage(movies: List<Movie>): Completable =
        storage.insertPopular(popularToDboConverter.convert(movies))

    override fun fetchMovieByIdFromStorage(id: String): Single<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun fetchMovieByIdFromNetwork(id: String): Single<MovieDetails> =
        remoteDataSource.fetchMovieById(id).map(movieResponseToDomain::convert)

    override fun saveMovieByIdToStorage(id: String): Completable {
        TODO("Not yet implemented")
    }
}