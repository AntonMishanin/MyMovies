package com.my.profile.watchlist.presentation

import com.my.core.presentation.BaseItem
import com.my.profile.R
import com.my.profile.databinding.ItemProgressBinding

internal class ProgressItem : BaseItem<ItemProgressBinding>(
    R.layout.item_progress,
    ItemProgressBinding::bind
) {

    override fun bind(viewBinding: ItemProgressBinding, position: Int) {

    }
}