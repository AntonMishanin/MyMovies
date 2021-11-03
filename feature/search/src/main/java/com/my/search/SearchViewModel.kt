package com.my.search

import android.text.Editable
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val searchSubject = PublishSubject.create<String>()
    private val mockContent = listOf(
        "test",
        "testhj",
        "testh",
        "test"
    )

    init {
        addTimeoutForSearch()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun addTimeoutForSearch() {
        val d = searchSubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                Timber.d(it)
                filterContentByText(it)
            }
        compositeDisposable.add(d)
    }

    fun onSearchTextChanged(text: Editable?) {
        searchSubject.onNext(text.toString())
    }

    private fun filterContentByText(searchText: String) {
        val d = Flowable.fromIterable(mockContent)
            .filter {
                it.contains(searchText)
            }
            .toList()
            .subscribe { list ->
                Timber.d("${list.size}")
            }
        compositeDisposable.add(d)
    }
}