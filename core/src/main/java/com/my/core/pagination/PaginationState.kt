package com.my.core.pagination

data class PaginationState(
    private var isProgress: Boolean = IS_PROGRESS_DEFAULT,
) {

    fun isAvailable() = !isProgress

    fun onStart() {
        isProgress = true
    }

    fun onFinish() {
        isProgress = false
    }

    companion object {
        private const val IS_PROGRESS_DEFAULT = false
    }
}