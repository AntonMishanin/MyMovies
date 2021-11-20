package com.my.profile.main.domain.entity

data class ProfileFeature(
    val titleViewType: TitleViewType,
    val contentViewType: ContentViewType
)

enum class ContentViewType {
    FAVORITE,
    WATCHLIST
}

/**
 * WithQuantity - добавляем в заголовок фичи количество элементов списка
 * OnlyTitle - в заголовке фичи только текст
 */

sealed class TitleViewType {
    data class WithQuantity(
        val quantity: String
    ) : TitleViewType() {
        companion object {
            fun emptyInstance() = WithQuantity(quantity = "")
        }
    }

    object OnlyTitle : TitleViewType()
}