package com.my.tv_shows.ui

import com.my.core.presentation.BaseItem
import com.my.tv_shows.R
import com.my.tv_shows.databinding.ItemUnknownErrorBinding

internal class UnknownErrorItem(
    private val refreshCallback: RefreshCallback
) : BaseItem<ItemUnknownErrorBinding>(
    R.layout.item_unknown_error,
    ItemUnknownErrorBinding::bind
) {

    override fun bind(viewBinding: ItemUnknownErrorBinding, position: Int) {
        viewBinding.tryAgain.setOnClickListener {
            refreshCallback.onRefreshClicked()
        }
    }
}