package com.my.movie.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.my.movie.storage.dto.NowPlayingEntity
import com.my.movie.storage.dto.PopularEntity
import com.my.movie.storage.dto.UpcomingEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlaying(list: List<NowPlayingEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopular(list: List<PopularEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcoming(list: List<UpcomingEntity>)

    @Query("SELECT * FROM NowPlayingEntity")
    fun loadAllNowPlaying(): Single<List<NowPlayingEntity>>

    @Query("SELECT * FROM PopularEntity")
    fun loadAllPopular(): Flowable<List<PopularEntity>>

    @Query("SELECT * FROM UpcomingEntity")
    fun loadAllUpcoming(): Flowable<List<UpcomingEntity>>
}