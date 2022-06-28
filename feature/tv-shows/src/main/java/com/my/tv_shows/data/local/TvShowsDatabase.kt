package com.my.tv_shows.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TvShowsDbo::class],
    version = 1,
    exportSchema = false
)
internal abstract class TvShowsDatabase : RoomDatabase() {

    abstract fun getTvShowsDao(): TvShowsDao
}