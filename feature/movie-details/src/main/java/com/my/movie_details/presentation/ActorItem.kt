package com.my.movie_details.presentation

import android.widget.ImageView
import android.widget.TextView
import com.my.movie_details.R
import com.my.movie_details.entity.MovieEntity
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class ActorItem(
    private val content: MovieEntity.Actor,
    private val onItemClicked: (MovieEntity.Actor) -> Unit
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val name = viewHolder.itemView.findViewById<TextView>(R.id.actor_name)
        val preview = viewHolder.itemView.findViewById<ImageView>(R.id.actor_preview)

        name.text = content.name
        Picasso.get().load(content.previewId).into(preview)

        viewHolder.itemView.setOnClickListener { onItemClicked(content) }
    }

    override fun getLayout(): Int = R.layout.item_actor
}