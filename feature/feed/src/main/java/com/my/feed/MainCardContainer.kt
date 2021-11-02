package com.my.feed

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class MainCardContainer(
    @StringRes
    private val title: Int,
    private val items: List<Item>
) : Item() {

    override fun getLayout() = R.layout.item_card

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val titleView = viewHolder.itemView.findViewById<TextView>(R.id.title_text_view)
        val itemsContainer = viewHolder.itemView.findViewById<RecyclerView>(R.id.items_container)

        titleView.text = viewHolder.itemView.context.getString(title)
        itemsContainer.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}
