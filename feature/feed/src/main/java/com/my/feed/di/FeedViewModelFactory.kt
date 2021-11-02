package com.my.feed.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.feed.FeedViewModel
import com.my.movie.MovieRepository

class FeedViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}