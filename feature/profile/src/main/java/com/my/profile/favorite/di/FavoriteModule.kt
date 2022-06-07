package com.my.profile.favorite.di

import com.my.core.di.Feature
import com.my.favorite.domain.usecase.FetchAllFavoriteUseCase
import com.my.profile.favorite.FavoriteViewModel
import dagger.Module
import dagger.Provides

@Module
internal class FavoriteModule {

    @Feature
    @Provides
    fun provideFavoriteViewModel(fetchAllFavoriteUseCase: FetchAllFavoriteUseCase) =
        FavoriteViewModel(fetchAllFavoriteUseCase)
}