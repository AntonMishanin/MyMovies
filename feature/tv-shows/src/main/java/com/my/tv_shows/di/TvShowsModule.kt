package com.my.tv_shows.di

import com.my.core.di.Feature
import com.my.core.di.SchedulersWrapper
import com.my.tv_shows.data.MemoryCacheDataSource
import com.my.tv_shows.data.TvRepositoryImpl
import com.my.tv_shows.data.converter.ToDomainExceptionConverter
import com.my.tv_shows.data.converter.TvShowsToDomainConverter
import com.my.tv_shows.data.remote.TvApi
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.ObserveTvShowsUseCase
import com.my.tv_shows.domain.RefreshTvShowsUseCase
import com.my.tv_shows.domain.ToggleOverviewUseCase
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.presentation.TvShowsPresenter
import com.my.tv_shows.presentation.TvShowsUiConverter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class TvShowsModule {

    @Provides
    fun provideTvShowsPresenter(
        observeTvShowsUseCase: ObserveTvShowsUseCase,
        toggleOverviewUseCase: ToggleOverviewUseCase,
        RefreshTvShowsUseCase: RefreshTvShowsUseCase,
        tvShowsUiConverter: TvShowsUiConverter,
        schedulersWrapper: SchedulersWrapper
    ) = TvShowsPresenter(
        observeTvShowsUseCase,
        toggleOverviewUseCase,
        RefreshTvShowsUseCase,
        tvShowsUiConverter,
        schedulersWrapper
    )

    @Provides
    fun provideFetchPopularTvShowsUseCase(tvRepository: TvRepository) =
        RefreshTvShowsUseCase(tvRepository)

    @Provides
    fun provideObserveTvShowsUseCase(tvRepository: TvRepository) =
        ObserveTvShowsUseCase(tvRepository)

    @Provides
    fun provideClickOnTvShowsUseCase(tvRepository: TvRepository) =
        ToggleOverviewUseCase(tvRepository)

    @Feature
    @Provides
    fun provideTvRepository(
        remoteDataSource: TvRemoteDataSource,
        tvShowsToDomainConverter: TvShowsToDomainConverter,
        toDomainExceptionConverter: ToDomainExceptionConverter,
        memoryCacheDataSource: MemoryCacheDataSource
    ): TvRepository = TvRepositoryImpl(
        remoteDataSource,
        tvShowsToDomainConverter,
        toDomainExceptionConverter,
        memoryCacheDataSource
    )

    @Provides
    fun provideMemoryCacheDataSource() = MemoryCacheDataSource()

    @Provides
    fun provideRemoteDataSource(tvApi: TvApi) = TvRemoteDataSource(tvApi)

    @Provides
    fun provideTvShowsApi(retrofit: Retrofit) = retrofit.create(TvApi::class.java)

    @Provides
    fun provideTvShowsToDomainConverter() = TvShowsToDomainConverter()

    @Provides
    fun provideToDomainExceptionConverter() = ToDomainExceptionConverter()

    @Provides
    fun provideTvShowsUiConverter() = TvShowsUiConverter()
}