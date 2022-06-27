package com.my.tv_shows.ui

import androidx.core.view.isVisible
import com.my.core.extensions.load
import com.my.core.presentation.BaseItem
import com.my.tv_shows.R
import com.my.tv_shows.databinding.ItemTvShowsBinding
import com.my.tv_shows.domain.TvShowsEntity

internal class TvShowsItem(
    private val content: TvShowsEntity,
    private val toggleOverviewCallback: ToggleOverviewCallback
) : BaseItem<ItemTvShowsBinding>(R.layout.item_tv_shows, ItemTvShowsBinding::bind) {

    override fun bind(viewBinding: ItemTvShowsBinding, position: Int) = with(viewBinding) {
        title.text = content.title
        rating.rating = content.rating
        imagePreview.load(content.imagePath)
        overview.text = content.overview
        overview.isVisible = content.isOverviewVisible
        root.setOnClickListener { toggleOverviewCallback.onToggleOverviewClicked(content) }
    }
}