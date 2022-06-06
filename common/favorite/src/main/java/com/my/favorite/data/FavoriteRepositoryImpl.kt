package com.my.favorite.data

import com.my.favorite.data.storage.FavoriteDao
import com.my.favorite.domain.repository.FavoriteRepository
import com.my.favorite.domain.usecase.FavoriteEntity
import io.reactivex.Flowable
import io.reactivex.Single

internal class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun insert(movieDetails: FavoriteEntity) = favoriteDao.insert(movieDetails.toDbo())

    override fun deleteById(id: Int) = favoriteDao.deleteById(id)

    override fun loadAll(): Flowable<List<FavoriteEntity>> = favoriteDao.loadAll()
        .map { favoritesList ->
            favoritesList.map { it.toDomain() }
        }

    override fun isFavoriteById(id: Int): Single<FavoriteEntity> =
        favoriteDao.loadById(id).map { it.toDomain() }
}