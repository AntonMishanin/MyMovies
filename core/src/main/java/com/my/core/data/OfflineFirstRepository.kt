package com.my.core.data

import com.my.core.di.SchedulersWrapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * D - domain
 * N - network
 * S - storage
 */
open class OfflineFirstRepository<D : Any, N : Any, S : Any>(
    private val schedulersWrapper: SchedulersWrapper,
    private val observeFromMemory: () -> Flowable<D>,
    private val fetchFromStorage: () -> Single<S>,
    private val fetchFromNetwork: () -> Single<N>,
    private val saveToStorage: (S) -> Completable,
    private val saveToMemory: (D) -> Unit,
    private val networkToStorage: (N) -> S,
    private val storageToDomain: (S) -> D,
    private val toDomainException: (Throwable) -> Throwable
) {

    fun observable(): Flowable<D> {
        return observeFromMemory.invoke()
            .doOnSubscribe {
                fetchFromStorage()
                    .onErrorResumeNext { refresh() }
                    .subscribeOn(schedulersWrapper.io())
                    .subscribe()
            }
    }

    fun refresh(): Single<D> {
        return fetchFromNetwork.invoke()
            .map { networkToStorage.invoke(it) }
            .flatMap { storage ->
                // It is here, because I would like to save to memory first of all
                val domain = storageToDomain.invoke(storage)
                saveToMemory.invoke(domain)

                saveToStorage.invoke(storage).toSingleDefault(storage)
            }
            .map { storageToDomain.invoke(it) }
            .onErrorResumeNext { fetchFromStorage(it) }
            .onErrorReturn { throwable ->
                throwable.printStackTrace()
                throw toDomainException.invoke(throwable)
            }
            .subscribeOn(schedulersWrapper.io())
    }

    private fun fetchFromStorage(throwable: Throwable = Throwable()): Single<D> {
        return fetchFromStorage.invoke()
            .map { storageToDomain.invoke(it) }
            .doOnSuccess { saveToMemory.invoke(it) }
            .onErrorResumeNext {
                it.printStackTrace()
                throw it
            }
            .map {
                if (it is List<*> && it.isEmpty()) {
                    throwable.printStackTrace()
                    throw throwable
                } else {
                    it
                }
            }
    }
}