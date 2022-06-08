package com.my.favorite.data

import com.my.favorite.domain.repository.FavoriteRepository
import com.my.favorite.domain.usecase.FavoriteEntity
import io.reactivex.Flowable
import io.reactivex.Single

internal class FavoriteRepositoryImpl(
    private val favoriteDataSource: FavoriteDataSource,
    private val toDomainConverter: FavoriteToDomainConverter,
    private val toDboConverter: FavoriteToDboConverter
) : FavoriteRepository {

    override fun insert(movieDetails: FavoriteEntity) =
        favoriteDataSource.insert(toDboConverter.convert(movieDetails))

    override fun deleteById(id: Int) = favoriteDataSource.deleteById(id)

    override fun loadAll(): Flowable<List<FavoriteEntity>> = favoriteDataSource.loadAll()
        .map { toDomainConverter.convert(it) }

    override fun isFavoriteById(id: Int): Single<FavoriteEntity> =
        favoriteDataSource.isFavoriteById(id).map { toDomainConverter.convert(it) }
}