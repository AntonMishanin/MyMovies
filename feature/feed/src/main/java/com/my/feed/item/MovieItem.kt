package com.my.feed.item

import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import com.my.domain.entity.Movie
import com.my.feed.R

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val description = viewHolder.itemView.findViewById<TextView>(R.id.description)
        val rating = viewHolder.itemView.findViewById<AppCompatRatingBar>(R.id.movie_rating)
        val parentView = viewHolder.itemView.findViewById<LinearLayout>(R.id.content)
        val preview = viewHolder.itemView.findViewById<ShapeableImageView>(R.id.image_preview)

        description.text = content.title
        rating.rating = content.rating
        parentView.setOnClickListener {
            onClick.invoke(content)
        }

        // TODO Получать из модели
        Picasso.get()
            .load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
            .into(preview)
    }
}
