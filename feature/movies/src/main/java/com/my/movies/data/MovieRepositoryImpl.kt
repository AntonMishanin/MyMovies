package com.my.movies.data

import com.my.movies.BuildConfig
import com.my.movies.data.dto.MovieDetailsResponse
import com.my.movies.data.storage.MovieDao
import com.my.movies.data.storage.dto.NowPlayingEntity
import com.my.movies.data.storage.dto.PopularEntity
import com.my.movies.data.storage.dto.UpcomingEntity
import com.my.movies.domain.Movie
import com.my.movies.domain.MovieDetails
import com.my.movies.domain.MovieRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

internal class MovieRepositoryImpl(
    private val remoteDataSource: MoviesDataSource,
    private val storage: MovieDao
) : MovieRepository {

    override fun fetchNowPlayingFromStorage(): Flowable<List<Movie>> =
        storage.loadAllNowPlaying().map { movies -> movies.map { toViewObject(it) } }

    override fun fetchNowPlayingFromNetwork(): Single<List<Movie>> =
        remoteDataSource.fetchNowPlaying().map { it.toValueObject() }

    override fun saveNowPlayingToStorage(movies: List<Movie>): Completable =
        storage.insertNowPlaying(movies.map { nowPlayingToDbo(it) })

    override fun fetchUpcomingFromStorage(): Flowable<List<Movie>?> =
        storage.loadAllUpcoming().map { movies -> movies.map { toViewObject(it) } }

    override fun fetchUpcomingFromNetwork(): Single<List<Movie>?> =
        remoteDataSource.fetchUpcoming().map { it.toValueObject() }

    override fun saveUpcomingToStorage(movies: List<Movie>): Completable =
        storage.insertUpcoming(movies.map { upcomingToDbo(it) })

    override fun fetchPopularFromStorage(): Flowable<List<Movie>?> =
        storage.loadAllPopular().map { movies -> movies.map { toViewObject(it) } }

    override fun fetchPopularFromNetwork(): Single<List<Movie>?> =
        remoteDataSource.fetchPopular().map { it.toValueObject() }

    override fun savePopularToStorage(movies: List<Movie>): Completable =
        storage.insertPopular(movies.map { popularToDbo(it) })

    override fun fetchMovieByIdFromStorage(id: String): Single<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun fetchMovieByIdFromNetwork(id: String): Single<MovieDetails> =
        remoteDataSource.fetchMovieById(id).map { it.toViewObject() }

    override fun saveMovieByIdToStorage(id: String): Completable {
        TODO("Not yet implemented")
    }
}

internal fun MovieDetailsResponse.toViewObject() = MovieDetails(
    id = this.id ?: throw NullPointerException("id must not be null"),
    posterPath = BuildConfig.BASE_IMAGE_PATH + this.posterPath,
    title = this.title ?: "",
    overview = this.overview ?: "",
    rating = this.voteAverage ?: 5f,
    studio = this.productionCompanies.toString(),
    genre = this.genres.toString(),
    year = this.releaseDate ?: ""
)

internal fun toViewObject(dbo: NowPlayingEntity) = Movie(
    id = dbo.id,
    title = dbo.title,
    voteAverage = dbo.voteAverage.toDouble(),
    posterPath = dbo.posterPath
)

internal fun toViewObject(dbo: PopularEntity) = Movie(
    id = dbo.id,
    title = dbo.title,
    voteAverage = dbo.voteAverage.toDouble(),
    posterPath = dbo.posterPath
)

internal fun toViewObject(dbo: UpcomingEntity) = Movie(
    id = dbo.id,
    title = dbo.title,
    voteAverage = dbo.voteAverage.toDouble(),
    posterPath = dbo.posterPath
)

internal fun nowPlayingToDbo(movie: Movie) = NowPlayingEntity(
    posterPath = movie.posterPath,
    adult = false,
    overview = "",
    releaseDate = "",
    id = movie.id,
    originalTitle = movie.title,
    originalLanguage = "",
    title = movie.title,
    backdropPath = "",
    popularity = 5f,
    voteCount = 5,
    video = false,
    voteAverage = movie.voteAverage.toFloat()
)

internal fun upcomingToDbo(movie: Movie) = UpcomingEntity(
    posterPath = movie.posterPath,
    adult = false,
    overview = "",
    releaseDate = "",
    id = movie.id,
    originalTitle = movie.title,
    originalLanguage = "",
    title = movie.title,
    backdropPath = "",
    popularity = 5f,
    voteCount = 5,
    video = false,
    voteAverage = movie.voteAverage.toFloat()
)

internal fun popularToDbo(movie: Movie) = PopularEntity(
    posterPath = movie.posterPath,
    adult = false,
    overview = "",
    releaseDate = "",
    id = movie.id,
    originalTitle = movie.title,
    originalLanguage = "",
    title = movie.title,
    backdropPath = "",
    popularity = 5f,
    voteCount = 5,
    video = false,
    voteAverage = movie.voteAverage.toFloat()
)