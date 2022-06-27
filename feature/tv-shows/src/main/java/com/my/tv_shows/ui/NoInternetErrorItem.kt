package com.my.tv_shows.ui

import com.my.core.presentation.BaseItem
import com.my.tv_shows.R
import com.my.tv_shows.databinding.ItemNoInternetBinding

internal class NoInternetErrorItem(
    private val tryAgainClickedCallback: OnTryAgainClickedCallback
) : BaseItem<ItemNoInternetBinding>(
    R.layout.item_no_internet,
    ItemNoInternetBinding::bind
) {

    override fun bind(viewBinding: ItemNoInternetBinding, position: Int) {
        viewBinding.tryAgain.setOnClickListener {
            tryAgainClickedCallback.onTryAgainClicked()
        }
    }
}