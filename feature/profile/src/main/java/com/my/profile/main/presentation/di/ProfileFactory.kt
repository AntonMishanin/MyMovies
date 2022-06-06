package com.my.profile.main.presentation.di

import android.content.Context
import com.my.movie.favorite.di.FavoriteFactory
import com.my.profile.main.data.MockRemoteDataSource
import com.my.profile.main.data.ProfileRepositoryImpl
import com.my.profile.main.domain.usecase.FetchFeaturesUseCase

class ProfileFactory {
    fun provideViewModelFactory(context: Context): ProfileViewModelFactory {
        val dataSource = MockRemoteDataSource()
        val profileRepository = ProfileRepositoryImpl(dataSource)
        val favoriteRepository = FavoriteFactory().provideRepository(context)
        val fetchFeaturesUseCase = FetchFeaturesUseCase(profileRepository, favoriteRepository)
        return ProfileViewModelFactory(fetchFeaturesUseCase)
    }
}