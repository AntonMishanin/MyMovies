package com.my.search.di

import com.my.search.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
internal class SearchModule {

    @Provides
    fun provideSearchViewModel() = SearchViewModel()
}