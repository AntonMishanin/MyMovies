package com.my.favorite.data

import com.my.favorite.domain.repository.FavoriteRepository
import com.my.favorite.domain.usecase.FavoriteEntity
import io.reactivex.Flowable
import io.reactivex.Single

internal class FavoriteRepositoryImpl(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {

    override fun insert(movieDetails: FavoriteEntity) =
        favoriteDataSource.insert(movieDetails.toDbo())

    override fun deleteById(id: Int) = favoriteDataSource.deleteById(id)

    override fun loadAll(): Flowable<List<FavoriteEntity>> = favoriteDataSource.loadAll()
        .map { favoritesList ->
            favoritesList.map { it.toDomain() }
        }

    override fun isFavoriteById(id: Int): Single<FavoriteEntity> =
        favoriteDataSource.isFavoriteById(id).map { it.toDomain() }
}