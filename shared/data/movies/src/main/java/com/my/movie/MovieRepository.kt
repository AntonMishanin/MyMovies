package com.my.movie

import android.util.Log
import com.my.domain.entity.Movie
import com.my.domain.entity.MovieDetails
import com.my.movie.dto.CreditsResponse
import com.my.movie.dto.MovieDetailsResponse
import com.my.movie.storage.MovieDao
import com.my.movie.storage.dto.NowPlayingEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(
    private val moviesDataSource: MoviesDataSource,
    private val localMovies: MovieDao
) {
    fun fetchNowPlaying(): Single<List<Movie>> {

        val d = moviesDataSource.fetchNowPlaying()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                localMovies.insertNowPlaying(it.toDbo())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        Log.d("EE", "SUCCESS INSERT")
                    }, {
                        Log.d("EE", "ERROR INSERT")
                    })
            }
            .subscribe({
                Log.d("EE", "SUCCESS")
            }, {
                Log.d("EE", "ERROR")
            })

        return localMovies.loadAllNowPlaying()
            .subscribeOn(Schedulers.io())
            .map { list -> list.map { it.toMovieViewObject() } }
            .doOnError { it.printStackTrace() }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchUpcoming(): Single<List<Movie>?> {
        return moviesDataSource.fetchUpcoming()
            .subscribeOn(Schedulers.io())
            .map { it.toValueObject() }
            .doOnError { it.printStackTrace() }//TODO: handle error
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchPopular(): Single<List<Movie>?> {
        return moviesDataSource.fetchPopular()
            .subscribeOn(Schedulers.io())
            .map { it.toValueObject() }
            .doOnError { it.printStackTrace() }//TODO: handle error
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchMovieById(id: String): Single<MovieDetails> {
        return moviesDataSource.fetchMovieById(id)
            .subscribeOn(Schedulers.io())
            .map { it.toViewObject() }
            .doOnError { it.printStackTrace() }//TODO: handle error
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchCreditsByMovieId(id: String): Single<CreditsResponse> {
        return moviesDataSource.fetchCreditsByMovieId(id)
            .subscribeOn(Schedulers.io())
            .doOnError { it.printStackTrace() }//TODO: handle error
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun MovieDetailsResponse.toViewObject() = MovieDetails(
    id = this.id ?: 0,
    posterPath = this.posterPath ?: "",
    title = this.title ?: "",
    overview = this.overview ?: "",
    rating = this.voteAverage ?: 5f,
    studio = this.productionCompanies.toString(),
    genre = this.genres.toString(),
    year = this.releaseDate ?: ""
)

fun NowPlayingEntity.toViewObject() = MovieDetails(
    id = this.id,
    posterPath = this.posterPath,
    title = this.title,
    overview = this.overview,
    rating = this.voteAverage,
    studio = "",
    genre = "",
    year = this.releaseDate
)

fun NowPlayingEntity.toMovieViewObject() = Movie(
    id = this.id,
    title = this.title,
    voteAverage = this.voteAverage.toDouble(),
    posterPath = this.posterPath
)