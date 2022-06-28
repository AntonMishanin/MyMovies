package com.my.tv_shows.ui

import com.my.core.presentation.BaseItem
import com.my.tv_shows.R
import com.my.tv_shows.databinding.ItemTvShowsProgressBinding

internal class ProgressItem : BaseItem<ItemTvShowsProgressBinding>(
    R.layout.item_tv_shows_progress,
    ItemTvShowsProgressBinding::bind
) {

    override fun bind(viewBinding: ItemTvShowsProgressBinding, position: Int) {
        viewBinding.shimmerRoot.startShimmerAnimation()
    }
}