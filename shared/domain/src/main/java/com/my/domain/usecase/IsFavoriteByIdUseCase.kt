package com.my.domain.usecase

import com.my.domain.repository.FavoriteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IsFavoriteByIdUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(id: Int, isFavorite: (Boolean) -> Unit) =
        repository.isFavoriteById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isFavorite(true)
            }, {
                isFavorite(false)
            })
}