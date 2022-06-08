package com.my.profile.main.data

import com.my.profile.main.data.converter.ProfileToDomainConverter.Companion.FAVORITE_TYPE
import com.my.profile.main.data.converter.ProfileToDomainConverter.Companion.WATCHLIST_TYPE
import com.my.profile.main.data.converter.ProfileToDomainConverter.Companion.WITH_QUANTITY
import com.my.profile.main.data.dto.FeatureTypeDto
import io.reactivex.Single

/**
 * Имитация получения списка фич с бекенда
 * И формирование TabLayout в зависимости от количества фич
 */

class MockRemoteDataSource {
    fun fetchFeatures(): Single<List<FeatureTypeDto>> = Single.just(listOfFeatures())

    private fun listOfFeatures() = listOf(
        getFeatureFavorite(),
        getFeatureWatchlist()
    )

    private fun getFeatureFavorite(): FeatureTypeDto = FeatureTypeDto(
        type = FAVORITE_TYPE,
        titleType = WITH_QUANTITY
    )

    private fun getFeatureWatchlist(): FeatureTypeDto = FeatureTypeDto(
        type = WATCHLIST_TYPE,
        titleType = WITH_QUANTITY
    )
}