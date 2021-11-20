package com.my.profile.main.data.converter

import com.my.profile.main.data.dto.FeatureTypeDto
import com.my.profile.main.domain.entity.ContentViewType
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.main.domain.entity.TitleViewType
import com.my.resources.converter.ConverterDtoToVo

object Converter : ConverterDtoToVo<FeatureTypeDto, ProfileFeature> {
    const val FAVORITE_TYPE = "FAVORITE_TYPE"
    const val WATCHLIST_TYPE = "WATCHLIST_TYPE"

    const val WITH_QUANTITY = "WITH_QUANTITY"
    const val ONLY_TITLE = "ONLY_TITLE"

    override fun toValueObject(dto: FeatureTypeDto): ProfileFeature {
        return when (dto.type) {
            FAVORITE_TYPE -> handleFavoriteType(dto.titleType)
            WATCHLIST_TYPE -> handleWatchlistType(dto.titleType)
            else -> throw IllegalArgumentException("Unknown type ${dto.type}")
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
}