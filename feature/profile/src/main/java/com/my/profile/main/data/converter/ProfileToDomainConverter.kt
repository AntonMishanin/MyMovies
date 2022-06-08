package com.my.profile.main.data.converter

import com.my.core.converter.IterableConverter
import com.my.profile.main.data.dto.FeatureTypeDto
import com.my.profile.main.domain.entity.ContentViewType
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.main.domain.entity.TitleViewType

class ProfileToDomainConverter : IterableConverter<FeatureTypeDto, ProfileFeature>() {

    override fun convert(input: FeatureTypeDto): ProfileFeature {
        return when (input.type) {
            FAVORITE_TYPE -> handleFavoriteType(input.titleType)
            WATCHLIST_TYPE -> handleWatchlistType(input.titleType)
            else -> throw IllegalArgumentException("Unknown type ${input.type}")
        }
    }

    private fun handleFavoriteType(titleType: String): ProfileFeature {
        return ProfileFeature(
            titleViewType = getTitleViewType(titleType),
            contentViewType = ContentViewType.FAVORITE
        )
    }

    private fun handleWatchlistType(titleType: String): ProfileFeature {
        return ProfileFeature(
            titleViewType = getTitleViewType(titleType),
            contentViewType = ContentViewType.WATCHLIST
        )
    }

    private fun getTitleViewType(titleType: String): TitleViewType {
        return when (titleType) {
            WITH_QUANTITY -> TitleViewType.WithQuantity.emptyInstance()
            ONLY_TITLE -> TitleViewType.OnlyTitle
            else -> throw IllegalArgumentException("Unknown type $titleType")
        }
    }

    companion object {
        const val FAVORITE_TYPE = "FAVORITE_TYPE"
        const val WATCHLIST_TYPE = "WATCHLIST_TYPE"

        const val WITH_QUANTITY = "WITH_QUANTITY"
        const val ONLY_TITLE = "ONLY_TITLE"
    }
}