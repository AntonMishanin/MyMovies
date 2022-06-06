package com.my.profile.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.favorite.domain.usecase.FetchAllFavoriteUseCase
import com.my.profile.favorite.FavoriteViewModel

class FavoriteViewModelFactory(
    private val fetchAllFavoriteUseCase: FetchAllFavoriteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(fetchAllFavoriteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}