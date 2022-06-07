package com.my.movies.feed.item

import com.my.core.presentation.BaseItem
import com.my.movies.R
import com.my.movies.databinding.ItemCardBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class MainCardContainer(
    private val title: String,
    private val items: List<MovieItem>
) : BaseItem<ItemCardBinding>(R.layout.item_card, ItemCardBinding::bind) {

    override fun bind(viewBinding: ItemCardBinding, position: Int) {
        viewBinding.titleTextView.text = title
        viewBinding.itemsContainer.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}
