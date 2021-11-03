package com.my.movie

import com.my.domain.entity.Movie
import com.my.movie.dto.CreditsResponse
import com.my.movie.dto.MovieDetailsResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(
    private val moviesDataSource: MoviesDataSource
) {
    fun fetchNowPlaying(): Single<List<Movie>?> {
        return moviesDataSource.fetchNowPlaying()
            .subscribeOn(Schedulers.io())
            .map { it.toValueObject() }
            .doOnError { it.printStackTrace() }//TODO: handle error
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

    fun fetchMovieById(id: String): Single<MovieDetailsResponse> {
        return moviesDataSource.fetchMovieById(id)
            .subscribeOn(Schedulers.io())
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