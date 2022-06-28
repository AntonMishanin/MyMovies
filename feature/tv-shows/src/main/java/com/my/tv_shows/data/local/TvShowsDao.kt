package com.my.tv_shows.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
internal interface TvShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<TvShowsDbo>): Completable

    @Query("SELECT * FROM TvShowsDbo")
    fun fetch(): Single<List<TvShowsDbo>>
}