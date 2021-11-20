package com.my.domain.repository

import com.my.domain.entity.MovieDetails
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface FavoriteRepository {
    fun insert(movieDetails: MovieDetails): Completable

    fun deleteById(id: Int): Completable

    fun loadAll(): Flowable<List<MovieDetails>>

    fun isFavoriteById(id: Int): Single<MovieDetails>
}