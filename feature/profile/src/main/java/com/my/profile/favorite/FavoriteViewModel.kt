package com.my.profile.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.domain.entity.MovieDetails
import com.my.domain.usecase.FetchAllFavoriteUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class FavoriteViewModel(
    fetchAllFavoriteUseCase: FetchAllFavoriteUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _content: MutableLiveData<List<MovieDetails>> = MutableLiveData()
    val content: LiveData<List<MovieDetails>> = _content

    init {
        fetchAllFavoriteUseCase()
            .subscribe({
                Timber.d("SUCCESS = ${it.size}")
                _content.value = it
            }, {
                Timber.d("Load favorite with error $it")
            }).toComposite()
    }

    private fun Disposable.toComposite() = compositeDisposable.add(this)
}