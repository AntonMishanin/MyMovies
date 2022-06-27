package com.my.tv_shows.di

import com.my.tv_shows.data.TvRepositoryImpl
import com.my.tv_shows.data.converter.ToDomainExceptionConverter
import com.my.tv_shows.data.converter.TvShowsToDomainConverter
import com.my.tv_shows.data.remote.TvApi
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.FetchPopularTvShowsUseCase
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
        fetchPopularTvShowsUseCase: FetchPopularTvShowsUseCase,
        tvShowsUiConverter: TvShowsUiConverter
    ) = TvShowsPresenter(fetchPopularTvShowsUseCase, tvShowsUiConverter)

    @Provides
    fun provideFetchPopularTvShowsUseCase(tvRepository: TvRepository) =
        FetchPopularTvShowsUseCase(tvRepository)

    @Provides
    fun provideTvRepository(
        remoteDataSource: TvRemoteDataSource,
        tvShowsToDomainConverter: TvShowsToDomainConverter,
        toDomainExceptionConverter: ToDomainExceptionConverter
    ): TvRepository = TvRepositoryImpl(
        remoteDataSource,
        tvShowsToDomainConverter,
        toDomainExceptionConverter
    )

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