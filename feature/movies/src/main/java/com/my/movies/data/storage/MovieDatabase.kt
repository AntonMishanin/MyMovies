package com.my.movies.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.movies.data.storage.dto.NowPlayingEntity
import com.my.movies.data.storage.dto.PopularEntity
import com.my.movies.data.storage.dto.UpcomingEntity

@Database(
    entities = [NowPlayingEntity::class, UpcomingEntity::class, PopularEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}