package com.my.core.pagination

data class PaginationConfig(
    private var page: Int = PAGE_DEFAULT,
    private val prefetchDistance: Int = PREFETCH_DISTANCE_DEFAULT,
    private val jumpRange: Int = JUMP_RANGE_DEFAULT
) {

    fun isEndReached(params: Pair<Int, Int>) = isEndReached(params.first, params.second)

    fun isEndReached(currentPosition: Int, itemsSize: Int) =
        currentPosition >= itemsSize - prefetchDistance

    fun nextPage(): Int {
        page += jumpRange
        return page
    }

    companion object {
        private const val PAGE_DEFAULT = 1
        private const val PREFETCH_DISTANCE_DEFAULT = 2
        private const val JUMP_RANGE_DEFAULT = 1
    }
}