package com.my.movies.data.di

import android.content.Context
import androidx.room.Room
import com.my.movies.data.MovieRepositoryImpl
import com.my.movies.data.MoviesApi
import com.my.movies.data.MoviesDataSource
import com.my.movies.data.converter.*
import com.my.movies.data.storage.MovieDao
import com.my.movies.data.storage.MovieDatabase
import com.my.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
internal class MoviesDataModule {

    @Provides
    fun provideMoviesApi(retrofit: Retrofit) = retrofit.create(MoviesApi::class.java)

    @Provides
    fun provideMoviesDataSource(moviesApi: MoviesApi) = MoviesDataSource(moviesApi)

    @Provides
    fun provideMoviesDao(movieDatabase: MovieDatabase) = movieDatabase.getMovieDao()

    @Provides
    fun provideMoviesRepositoryImpl(
        moviesDataSource: MoviesDataSource,
        moviesDao: MovieDao,
        popularToDboConverter: PopularToDboConverter,
        upcomingToDboConverter: UpcomingToDboConverter,
        nowPlayingToDboConverter: NowPlayingToDboConverter,
        upcomingToDomainConverter: UpcomingToDomainConverter,
        popularToDomainConverter: PopularToDomainConverter,
        nowPlayingToDomainConverter: NowPlayingToDomainConverter,
        movieResponseToDomain: MovieResponseToDomain
    ): MovieRepository = MovieRepositoryImpl(
        moviesDataSource, moviesDao,
        popularToDboConverter,
        upcomingToDboConverter,
        nowPlayingToDboConverter,
        upcomingToDomainConverter,
        popularToDomainConverter,
        nowPlayingToDomainConverter,
        movieResponseToDomain
    )

    @Provides
    fun popularToDboConverter() = PopularToDboConverter()

    @Provides
    fun upcomingToDboConverter() = UpcomingToDboConverter()

    @Provides
    fun nowPlayingToDboConverter() = NowPlayingToDboConverter()

    @Provides
    fun upcomingToDomainConverter() = UpcomingToDomainConverter()

    @Provides
    fun popularToDomainConverter() = PopularToDomainConverter()

    @Provides
    fun nowPlayingToDomainConverter() = NowPlayingToDomainConverter()

    @Provides
    fun movieResponseToDomain() = MovieResponseToDomain()

    @Provides
    fun provideMoviesDatabase(
        context: Context,
        @Named(DATABASE_NAME_KEY) name: String
    ): MovieDatabase {
        return Room
            .databaseBuilder(context, MovieDatabase::class.java, name)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Named(DATABASE_NAME_KEY)
    fun provideMoviesDatabaseName() = "Movie_database"

    private companion object {
        const val DATABASE_NAME_KEY = "DATABASE_NAME"
    }
}