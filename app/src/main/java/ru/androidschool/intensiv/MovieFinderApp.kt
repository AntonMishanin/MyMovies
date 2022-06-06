@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package ru.androidschool.intensiv

import android.app.Application
import com.my.feed.di.FeedDepsStore
import ru.androidschool.intensiv.di.AppComponent
import ru.androidschool.intensiv.di.DaggerAppComponent
import timber.log.Timber

class MovieFinderApp : Application() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(application = this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        FeedDepsStore.deps = appComponent
        initDebugTools()
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
