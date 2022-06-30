package com.my.tv_shows.domain

// TODO: think about var -> val
data class PaginationConfig(
    var page: Int = PAGE_DEFAULT,
    val isProgress: Boolean = IS_PROGRESS_DEFAULT,
    val prefetchDistance: Int = PREFETCH_DISTANCE_DEFAULT,
    val jumpRange: Int = JUMP_RANGE_DEFAULT
) {

    companion object {
        private const val PAGE_DEFAULT = 1
        private const val IS_PROGRESS_DEFAULT = false
        private const val PREFETCH_DISTANCE_DEFAULT = 2
        private const val JUMP_RANGE_DEFAULT = 1
    }
}
