package com.my.movies.feed.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.my.movies.domain.FetchCompositeMovieUseCase
import com.my.movies.feed.FeedFragment
import dagger.Component
import kotlin.properties.Delegates.notNull

@Component(dependencies = [FeedDeps::class])
interface FeedComponent {

    fun inject(fragment: FeedFragment)

    @Component.Builder
    interface Builder {

        fun deps(feedDeps: FeedDeps): Builder

        fun build(): FeedComponent
    }
}

interface FeedDeps {
    val fetchCompositeMovieUseCase: FetchCompositeMovieUseCase
}

interface FeedDepsProvider {

    @get: RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: FeedDeps

    companion object : FeedDepsProvider by FeedDepsStore
}

object FeedDepsStore : FeedDepsProvider {
    override var deps: FeedDeps by notNull()
}

internal class FeedComponentViewModel : ViewModel() {
    val component = DaggerFeedComponent.builder().deps(FeedDepsProvider.deps).build()
}