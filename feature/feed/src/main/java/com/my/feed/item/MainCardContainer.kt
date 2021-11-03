package com.my.feed.item

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.my.feed.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class MainCardContainer(
    private val title: String,
    private val items: List<Item>
) : Item() {

    override fun getLayout() = R.layout.item_card

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val titleView = viewHolder.itemView.findViewById<TextView>(R.id.title_text_view)
        val itemsContainer = viewHolder.itemView.findViewById<RecyclerView>(R.id.items_container)

        titleView.text = title
        itemsContainer.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}
