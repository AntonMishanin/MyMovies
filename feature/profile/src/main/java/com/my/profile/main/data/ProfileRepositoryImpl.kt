package com.my.profile.main.data

import com.my.profile.main.data.converter.Converter
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.main.domain.repository.ProfileRepository
import io.reactivex.Flowable

class ProfileRepositoryImpl(
    private val dataSource: MockRemoteDataSource
) : ProfileRepository {
    override fun fetchFeatures(): Flowable<List<ProfileFeature>> =
        dataSource.fetchFeatures().map { Converter.toValueObject(it) }.toFlowable()
}