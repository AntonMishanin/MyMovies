package com.my.feed.item

import android.view.View
import com.my.feed.R
import com.my.feed.databinding.ItemCardBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class MainCardContainer(
    private val title: String,
    private val items: List<MovieItem>
) : BindableItem<ItemCardBinding>() {

    override fun getLayout() = R.layout.item_card

    override fun initializeViewBinding(view: View) = ItemCardBinding.bind(view)

    override fun bind(viewBinding: ItemCardBinding, position: Int) {
        viewBinding.titleTextView.text = title
        viewBinding.itemsContainer.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}
