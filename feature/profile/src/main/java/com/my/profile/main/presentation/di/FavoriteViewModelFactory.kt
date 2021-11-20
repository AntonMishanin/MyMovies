package com.my.profile.main.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.profile.main.domain.usecase.FetchFeaturesUseCase
import com.my.profile.main.presentation.ProfileViewModel

class ProfileViewModelFactory(
    private val fetchFeaturesUseCase: FetchFeaturesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(fetchFeaturesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}