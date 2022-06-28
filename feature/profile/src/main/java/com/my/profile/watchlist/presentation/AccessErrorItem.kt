package com.my.profile.watchlist.presentation

import com.my.core.presentation.BaseItem
import com.my.profile.R
import com.my.profile.databinding.ItemAccessErrorBinding

internal class AccessErrorItem : BaseItem<ItemAccessErrorBinding>(
    R.layout.item_access_error,
    ItemAccessErrorBinding::bind
) {

    override fun bind(viewBinding: ItemAccessErrorBinding, position: Int) = Unit
}