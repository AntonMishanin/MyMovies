@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package ru.androidschool.intensiv

import android.app.Application
import com.my.core.di.DependenciesProvider
import com.my.core.di.FeatureDependencies
import com.my.movies.detail.di.MovieDetailsDependencies
import com.my.movies.feed.di.FeedDependencies
import com.my.search.di.SearchDependencies
import ru.androidschool.intensiv.di.AppComponent
import ru.androidschool.intensiv.di.DaggerAppComponent
import timber.log.Timber
import kotlin.reflect.KClass

class MovieFinderApp : Application(), DependenciesProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(application = this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
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

    override fun <T : FeatureDependencies> provide(kClass: KClass<T>): T {
        return when (kClass) {
            MovieDetailsDependencies::class -> appComponent.provideMovieDetailsDependencies()
            FeedDependencies::class -> appComponent.provideFeedDependencies()
            SearchDependencies::class -> appComponent.provideSearchDependencies()
            else -> throw IllegalArgumentException("Unknown dependencies kClass")
        } as T
    }
}
