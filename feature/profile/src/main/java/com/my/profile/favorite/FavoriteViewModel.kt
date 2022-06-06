package com.my.profile.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.domain.entity.MovieDetails
import com.my.domain.usecase.FetchAllFavoriteUseCase
import com.my.resources.mvvm.RxViewModel
import timber.log.Timber

class FavoriteViewModel(
    fetchAllFavoriteUseCase: FetchAllFavoriteUseCase
) : RxViewModel() {

    private val _content: MutableLiveData<List<MovieDetails>> = MutableLiveData()
    val content: LiveData<List<MovieDetails>> = _content

    init {
        fetchAllFavoriteUseCase()
            .subscribe({
                _content.value = it
            }, {
                Timber.e("Load favorite with error $it")
            }).addToComposite()
    }
}