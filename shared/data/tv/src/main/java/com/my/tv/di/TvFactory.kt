package com.my.tv.di

import com.my.domain.repository.TvRepository
import com.my.remote.RemoteFactory
import com.my.tv.TvRepositoryImpl
import com.my.tv.remote.TvApi
import com.my.tv.remote.TvRemoteDataSource

class TvFactory {
    fun provideRepository(): TvRepository {
        val retrofit = RemoteFactory().provideRetrofit()
        val api = retrofit.create(TvApi::class.java)
        val remoteDataSource = TvRemoteDataSource(api)
        return TvRepositoryImpl(remoteDataSource)
    }
}