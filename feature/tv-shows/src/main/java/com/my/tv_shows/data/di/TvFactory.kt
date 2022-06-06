package com.my.tv_shows.data.di

import com.my.core.data.RemoteFactory
import com.my.tv_shows.data.TvRepositoryImpl
import com.my.tv_shows.data.remote.TvApi
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository

class TvFactory {
    fun provideRepository(): TvRepository {
        val retrofit = RemoteFactory().provideRetrofit()
        val api = retrofit.create(TvApi::class.java)
        val remoteDataSource = TvRemoteDataSource(api)
        return TvRepositoryImpl(remoteDataSource)
    }
}