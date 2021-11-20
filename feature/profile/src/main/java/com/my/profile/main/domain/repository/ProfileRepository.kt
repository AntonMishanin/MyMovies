package com.my.profile.main.domain.repository

import com.my.profile.main.domain.entity.ProfileFeature
import io.reactivex.Flowable

interface ProfileRepository {
    fun fetchFeatures(): Flowable<List<ProfileFeature>>
}