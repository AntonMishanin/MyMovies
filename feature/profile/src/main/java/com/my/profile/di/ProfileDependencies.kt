package com.my.profile.di

import com.my.core.di.FeatureDependencies
import com.my.favorite.domain.repository.FavoriteRepository
import com.my.favorite.domain.usecase.FetchAllFavoriteUseCase

interface ProfileDependencies : FeatureDependencies {

    fun provideFavoriteRepository(): FavoriteRepository

    fun provideFetchAllFavoriteUseCase(): FetchAllFavoriteUseCase
}