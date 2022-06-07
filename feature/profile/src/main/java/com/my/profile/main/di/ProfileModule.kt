package com.my.profile.main.di

import com.my.core.di.Feature
import com.my.favorite.domain.repository.FavoriteRepository
import com.my.profile.main.data.MockRemoteDataSource
import com.my.profile.main.data.ProfileRepositoryImpl
import com.my.profile.main.domain.repository.ProfileRepository
import com.my.profile.main.domain.usecase.FetchFeaturesUseCase
import com.my.profile.main.presentation.ProfileViewModel
import dagger.Module
import dagger.Provides

@Module
internal class ProfileModule {

    @Feature
    @Provides
    fun provideViewModel(fetchFeaturesUseCase: FetchFeaturesUseCase) =
        ProfileViewModel(fetchFeaturesUseCase)

    @Provides
    fun provideFetchFeaturesUseCase(
        profileRepository: ProfileRepository,
        favoriteRepository: FavoriteRepository
    ) = FetchFeaturesUseCase(profileRepository, favoriteRepository)

    @Provides
    fun provideProfileRepository(dataSource: MockRemoteDataSource): ProfileRepository =
        ProfileRepositoryImpl(dataSource)

    @Provides
    fun provideDataSource() = MockRemoteDataSource()
}