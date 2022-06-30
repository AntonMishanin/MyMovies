package com.my.tv_shows.di

import com.my.core.di.SchedulersWrapper
import com.my.tv_shows.domain.PaginationConfig
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.domain.TvSeasonsInteractor
import com.my.tv_shows.presentation.TvShowsPresenter
import com.my.tv_shows.presentation.TvShowsUiConverter
import dagger.Module
import dagger.Provides

@Module
internal class TvShowsModule {

    @Provides
    fun provideTvShowsPresenter(
        tvSeasonsInteractor: TvSeasonsInteractor,
        tvShowsUiConverter: TvShowsUiConverter,
        schedulersWrapper: SchedulersWrapper
    ) = TvShowsPresenter(
        tvSeasonsInteractor,
        tvShowsUiConverter,
        schedulersWrapper
    )

    @Provides
    fun provideTvSeasonsInteractor(
        tvRepository: TvRepository,
        paginationConfig: PaginationConfig
    ) = TvSeasonsInteractor(tvRepository, paginationConfig)

    @Provides
    fun providePaginationConfig() = PaginationConfig()

    @Provides
    fun provideTvShowsUiConverter() = TvShowsUiConverter()
}