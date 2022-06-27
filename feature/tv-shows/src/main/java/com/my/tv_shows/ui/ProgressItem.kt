package com.my.tv_shows.ui

import com.my.core.presentation.BaseItem
import com.my.tv_shows.R
import com.my.tv_shows.databinding.ItemProgressBinding

internal class ProgressItem : BaseItem<ItemProgressBinding>(
    R.layout.item_progress,
    ItemProgressBinding::bind
) {

    override fun bind(viewBinding: ItemProgressBinding, position: Int) {
        viewBinding.shimmerRoot.startShimmerAnimation()
    }
}