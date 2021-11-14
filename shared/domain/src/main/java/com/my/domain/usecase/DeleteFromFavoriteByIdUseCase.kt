package com.my.domain.usecase

import com.my.domain.repository.FavoriteRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DeleteFromFavoriteByIdUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(id: Int): Completable = repository.deleteById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}