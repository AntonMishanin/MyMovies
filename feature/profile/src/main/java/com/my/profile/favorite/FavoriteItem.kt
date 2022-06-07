package com.my.profile.favorite

import com.my.core.extensions.load
import com.my.core.presentation.BaseItem
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.profile.R
import com.my.profile.databinding.ItemSmallBinding

class FavoriteItem(
    private val content: FavoriteEntity
) : BaseItem<ItemSmallBinding>(R.layout.item_small, ItemSmallBinding::bind) {

    override fun bind(viewBinding: ItemSmallBinding, position: Int) {
        viewBinding.imagePreview.load(R.drawable.ic_avatar)
    }
}