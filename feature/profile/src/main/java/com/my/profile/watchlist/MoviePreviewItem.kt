package com.my.profile.watchlist

import com.my.core.extensions.load
import com.my.core.presentation.BaseItem
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.profile.R
import com.my.profile.databinding.ItemSmallBinding

class MoviePreviewItem(
    private val content: FavoriteEntity,
    private val onClick: (FavoriteEntity) -> Unit
) : BaseItem<ItemSmallBinding>(R.layout.item_small, ItemSmallBinding::bind) {

    override fun bind(viewBinding: ItemSmallBinding, position: Int) {
        viewBinding.imagePreview.setOnClickListener {
            onClick.invoke(content)
        }

        viewBinding.imagePreview.load(content.posterPath)
    }
}
