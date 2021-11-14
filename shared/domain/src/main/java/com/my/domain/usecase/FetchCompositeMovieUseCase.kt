package com.my.domain.usecase

import com.my.domain.entity.CompositeMovieEntity
import com.my.domain.entity.Movie
import com.my.domain.repository.MovieRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FetchCompositeMovieUseCase(private val repository: MovieRepository) {

    private val compositeDisposable = CompositeDisposable()

    operator fun invoke(): Flowable<CompositeMovieEntity> {
        fetchFreshNowPlayingAndSaveToStorage()
        fetchFreshPopularAndSaveToStorage()
        fetchFreshUpcomingAndSaveToStorage()

        return Flowable.zip(
            repository.fetchNowPlayingFromStorage(),
            repository.fetchPopularFromStorage(),
            repository.fetchUpcomingFromStorage()
        ) { nowPlaying, popular, upcoming ->
            CompositeMovieEntity(
                nowPlaying = nowPlaying,
                popular = popular,
                upcoming = upcoming
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun fetchFreshNowPlayingAndSaveToStorage() {
        repository.fetchNowPlayingFromNetwork()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    val d = repository.saveNowPlayingToStorage(t)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Timber.e("Successful saving now playing movies to the storage")
                            compositeDisposable.clear()
                        }
                    compositeDisposable.add(d)
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    dispose()
                }
            })
    }

    private fun fetchFreshPopularAndSaveToStorage() {
        repository.fetchPopularFromNetwork()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    val d = repository.savePopularToStorage(t)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Timber.e("Successful saving popular movies to the storage")
                            compositeDisposable.clear()
                        }
                    compositeDisposable.add(d)
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    dispose()
                }
            })
    }

    private fun fetchFreshUpcomingAndSaveToStorage() {
        repository.fetchUpcomingFromNetwork()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    val d = repository.saveUpcomingToStorage(t)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Timber.e("Successful saving upcoming movies to the storage")
                            compositeDisposable.clear()
                        }
                    compositeDisposable.add(d)
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    dispose()
                }
            })
    }
}