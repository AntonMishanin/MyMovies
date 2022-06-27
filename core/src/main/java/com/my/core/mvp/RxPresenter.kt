package com.my.core.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxPresenter<T : BaseView>(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    protected var view: T? = null
        private set

    fun onViewAttached(view: T) {
        this.view = view
        onViewReady()
    }

    open fun onViewDetached() {
        view = null
        compositeDisposable.dispose()
    }

    protected open fun onViewReady() = Unit

    protected fun Disposable.addToComposite() = compositeDisposable.add(this)
}