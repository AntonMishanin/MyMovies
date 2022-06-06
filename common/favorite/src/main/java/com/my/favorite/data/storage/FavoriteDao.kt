package com.my.favorite.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.my.favorite.data.dto.FavoriteDbo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
internal interface FavoriteDao {
    @Insert(onConflict = REPLACE)
    fun insert(favorite: FavoriteDbo): Completable

    @Query("DELETE FROM FavoriteDbo WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("SELECT * FROM FavoriteDbo")
    fun loadAll(): Flowable<List<FavoriteDbo>>

    @Query("SELECT * FROM FavoriteDbo WHERE id = :id")
    fun loadById(id: Int): Single<FavoriteDbo>
}