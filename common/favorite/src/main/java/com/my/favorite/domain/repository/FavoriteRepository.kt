package com.my.favorite.domain.repository

import com.my.favorite.domain.usecase.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface FavoriteRepository {
    fun insert(movieDetails: FavoriteEntity): Completable

    fun deleteById(id: Int): Completable

    fun loadAll(): Flowable<List<FavoriteEntity>>

    fun isFavoriteById(id: Int): Single<FavoriteEntity>
}