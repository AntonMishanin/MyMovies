package com.my.search

import android.text.Editable
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val search = PublishSubject.create<String>()

    init {
        val d = search
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                Timber.d(it)
            }
        compositeDisposable.add(d)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun onSearchTextChanged(text: Editable?) {
        search.onNext(text.toString())
    }
}