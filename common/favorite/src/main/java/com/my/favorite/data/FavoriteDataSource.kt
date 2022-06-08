package com.my.favorite.data

import com.my.favorite.data.dto.FavoriteDbo
import com.my.favorite.data.storage.FavoriteDao
import io.reactivex.Flowable
import io.reactivex.Single

internal class FavoriteDataSource(
    private val favoriteDao: FavoriteDao
) {

    fun insert(movieDetails: FavoriteDbo) = favoriteDao.insert(movieDetails)

    fun deleteById(id: Int) = favoriteDao.deleteById(id)

    fun loadAll(): Flowable<List<FavoriteDbo>> = favoriteDao.loadAll()

    fun isFavoriteById(id: Int): Single<FavoriteDbo> =
        favoriteDao.loadById(id)
}