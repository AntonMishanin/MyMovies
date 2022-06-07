package com.my.profile.di

import com.my.core.di.Feature
import com.my.profile.favorite.FavoriteViewModel
import com.my.profile.favorite.di.FavoriteModule
import com.my.profile.main.di.ProfileModule
import com.my.profile.main.presentation.ProfileViewModel
import com.my.profile.watchlist.WatchlistViewModel
import com.my.profile.watchlist.di.WatchlistModule
import dagger.Component

@Feature
@Component(
    modules = [ProfileModule::class, WatchlistModule::class, FavoriteModule::class],
    dependencies = [ProfileDependencies::class]
)
internal interface ProfileComponent {

    fun provideFavoriteViewModel(): FavoriteViewModel

    fun provideWatchlistViewModel(): WatchlistViewModel

    fun provideProfileViewModel(): ProfileViewModel

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ProfileDependencies): Builder

        fun build(): ProfileComponent
    }
}