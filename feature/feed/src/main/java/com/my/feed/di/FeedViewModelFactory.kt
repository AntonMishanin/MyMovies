package com.my.feed.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.domain.usecase.FetchCompositeMovieUseCase
import com.my.feed.FeedViewModel
import javax.inject.Inject

class FeedViewModelFactory @Inject constructor(
    private val fetchCompositeMovieUseCase: FetchCompositeMovieUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(fetchCompositeMovieUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}