package ru.androidschool.intensiv.di

import android.app.Application
import com.my.domain.usecase.FetchCompositeMovieUseCase
import com.my.feed.di.FeedDeps
import com.my.movie.di.MoviesFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : FeedDeps {

    override val fetchCompositeMovieUseCase: FetchCompositeMovieUseCase

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {

    @[Provides AppScope]
    fun provideFetchCompositeMovieUseCase(application: Application): FetchCompositeMovieUseCase {
        val movieRepository = MoviesFactory().provideMovieRepository(application.applicationContext)
        return FetchCompositeMovieUseCase(movieRepository)
    }
}

@Scope
annotation class AppScope