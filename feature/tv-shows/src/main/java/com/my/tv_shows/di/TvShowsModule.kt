package com.my.tv_shows.di

import com.my.core.di.SchedulersWrapper
import com.my.tv_shows.domain.ObserveTvShowsUseCase
import com.my.tv_shows.domain.RefreshTvShowsUseCase
import com.my.tv_shows.domain.ToggleOverviewUseCase
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.presentation.TvShowsPresenter
import com.my.tv_shows.presentation.TvShowsUiConverter
import dagger.Module
import dagger.Provides

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

    @Provides
    fun provideTvShowsUiConverter() = TvShowsUiConverter()
}