package com.my.tv_shows.presentation

import com.my.core.extensions.load
import com.my.core.presentation.BaseItem
import com.my.tv_shows.R
import com.my.tv_shows.databinding.ItemTvShowsBinding
import com.my.tv_shows.domain.TvShowsEntity

class TvShowsItem(
    private val content: TvShowsEntity,
    private val onItemClicked: (TvShowsEntity) -> Unit
) : BaseItem<ItemTvShowsBinding>(R.layout.item_tv_shows, ItemTvShowsBinding::bind) {

    override fun bind(viewBinding: ItemTvShowsBinding, position: Int) {
        viewBinding.title.text = content.title
        viewBinding.rating.rating = content.rating
        viewBinding.imagePreview.load(content.imagePath)

        viewBinding.root.setOnClickListener { onItemClicked(content) }
    }
}