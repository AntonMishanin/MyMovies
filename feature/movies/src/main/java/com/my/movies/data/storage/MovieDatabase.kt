package com.my.movies.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        @Volatile
        private var instance: MovieDatabase? = null

        fun getFavoriteDatabase(context: Context): MovieDatabase {
            if (instance != null) {
                return instance as MovieDatabase
            }
            synchronized(this) {
                instance = Room
                    .databaseBuilder(context, MovieDatabase::class.java, "Movie_database")
                    .fallbackToDestructiveMigration()
                    .build()

                return instance as MovieDatabase
            }
        }
    }
}