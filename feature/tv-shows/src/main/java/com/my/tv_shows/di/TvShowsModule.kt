package com.my.tv_shows.di

import com.my.core.di.SchedulersWrapper
import com.my.core.pagination.Pagination
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
        paginationConfig: Pagination
    ) = TvSeasonsInteractor(tvRepository, paginationConfig)

    @Provides
    fun providePagination() = Pagination()

    @Provides
    fun provideTvShowsUiConverter() = TvShowsUiConverter()
}