package com.my.tv_shows.data.di

import android.content.Context
import androidx.room.Room
import com.my.core.di.Feature
import com.my.core.di.SchedulersWrapper
import com.my.tv_shows.data.*
import com.my.tv_shows.data.local.LocalDataSource
import com.my.tv_shows.data.local.TvShowsDao
import com.my.tv_shows.data.local.TvShowsDatabase
import com.my.tv_shows.data.remote.TvApi
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
internal class TvShowsDataModule {

    @Feature
    @Provides
    fun provideTvRepository(
        remoteDataSource: TvRemoteDataSource,
        toDomainExceptionConverter: ToDomainExceptionConverter,
        memoryCacheDataSource: MemoryCacheDataSource,
        localDataSource: LocalDataSource,
        remoteToLocalConverter: RemoteToLocalConverter,
        localToDomainConverter: LocalToDomainConverter,
        schedulersWrapper: SchedulersWrapper
    ): TvRepository = TvRepositoryImpl(
        remoteDataSource,
        toDomainExceptionConverter,
        memoryCacheDataSource,
        localDataSource,
        remoteToLocalConverter,
        localToDomainConverter,
        schedulersWrapper
    )

    @Provides
    fun provideMemoryCacheDataSource() = MemoryCacheDataSource()

    @Provides
    fun provideRemoteDataSource(tvApi: TvApi) = TvRemoteDataSource(tvApi)

    @Provides
    fun provideTvShowsApi(retrofit: Retrofit) = retrofit.create(TvApi::class.java)

    @Provides
    fun provideToDomainExceptionConverter() = ToDomainExceptionConverter()

    @Provides
    fun provideLocalDataSource(tvShowsDao: TvShowsDao) = LocalDataSource(tvShowsDao)

    @Provides
    fun provideRemoteToLocalConverter() = RemoteToLocalConverter()

    @Provides
    fun provideLocalToDomainConverter() = LocalToDomainConverter()

    @Provides
    fun provideTvShowsDao(tvShowsDatabase: TvShowsDatabase) = tvShowsDatabase.getTvShowsDao()

    @Provides
    fun provideTvShowsDatabase(
        context: Context,
        @Named(DATABASE_NAME_KEY) name: String
    ): TvShowsDatabase {
        return Room
            .databaseBuilder(context, TvShowsDatabase::class.java, name)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Named(DATABASE_NAME_KEY)
    fun provideTvShowsDatabaseName() = "Tv_shows_database"

    private companion object {
        const val DATABASE_NAME_KEY = "DATABASE_NAME"
    }
}