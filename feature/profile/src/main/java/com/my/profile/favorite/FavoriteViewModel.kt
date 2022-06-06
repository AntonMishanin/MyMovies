package com.my.profile.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.core.mvvm.RxViewModel
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.favorite.domain.usecase.FetchAllFavoriteUseCase
import timber.log.Timber

class FavoriteViewModel(
    fetchAllFavoriteUseCase: FetchAllFavoriteUseCase
) : RxViewModel() {

    private val _content: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()
    val content: LiveData<List<FavoriteEntity>> = _content

    init {
        fetchAllFavoriteUseCase()
            .subscribe({
                _content.value = it
            }, {
                Timber.e("Load favorite with error $it")
            }).addToComposite()
    }
}