package com.my.profile.main.domain.usecase

import com.my.core.extensions.applySchedulers
import com.my.favorite.domain.repository.FavoriteRepository
import com.my.profile.main.domain.entity.ContentViewType
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.main.domain.entity.TitleViewType
import com.my.profile.main.domain.repository.ProfileRepository
import io.reactivex.Flowable

/**
 * Имитация получения списка фич с бекенда
 * И формирование TabLayout в зависимости от количества фич
 */

class FetchFeaturesUseCase(
    private val profileRepository: ProfileRepository,
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(): Flowable<List<ProfileFeature>> {
        return Flowable.zip(
            getMockWatchlist(),
            favoriteRepository.loadAll(),
            profileRepository.fetchFeatures(),
        ) { movies, favorites, features ->
            features.map { feature ->
                when (feature.contentViewType) {
                    ContentViewType.FAVORITE -> handleFeatureTitle(feature, favorites.size)
                    ContentViewType.WATCHLIST -> handleFeatureTitle(feature, movies.size)
                }
            }
        }.applySchedulers()
    }

    private fun handleFeatureTitle(
        feature: ProfileFeature,
        quantity: Int
    ): ProfileFeature {
        return when (feature.titleViewType) {
            is TitleViewType.WithQuantity -> {
                val titleViewType = feature.titleViewType.copy(quantity = quantity.toString())
                feature.copy(titleViewType = titleViewType)
            }
            TitleViewType.OnlyTitle -> feature
        }
    }

    private fun getMockWatchlist() = Flowable.just(listOf<String>())
}