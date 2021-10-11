package com.my.tv_shows.presentation

import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.my.tv_shows.R
import com.my.tv_shows.entity.TvShowsEntity
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class TvShowsItem(
    private val content: TvShowsEntity,
    private val onItemClicked: (TvShowsEntity) -> Unit
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val title = viewHolder.itemView.findViewById<TextView>(R.id.title)
        val rating = viewHolder.itemView.findViewById<RatingBar>(R.id.rating)
        val preview = viewHolder.itemView.findViewById<ShapeableImageView>(R.id.image_preview)

        title.text = content.title
        rating.rating = content.rating
        Picasso.get().load(content.imagePath).into(preview)

        viewHolder.itemView.setOnClickListener { onItemClicked(content) }
    }

    override fun getLayout(): Int = R.layout.item_tv_shows
}