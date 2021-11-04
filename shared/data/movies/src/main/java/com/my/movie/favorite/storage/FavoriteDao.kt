package com.my.movie.favorite.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.my.movie.favorite.dto.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
internal interface FavoriteDao {
    @Insert(onConflict = REPLACE)
    fun insert(favorite: FavoriteEntity): Completable

    @Query("DELETE FROM FavoriteEntity WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("SELECT * FROM FavoriteEntity")
    fun loadAll(): Flowable<List<FavoriteEntity>>

    @Query("SELECT * FROM FavoriteEntity WHERE id = :id")
    fun loadById(id: Int): Single<FavoriteEntity>
}