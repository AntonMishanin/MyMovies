package com.my.movie_details.presentation

import android.view.View
import com.my.movie_details.R
import com.my.movie_details.databinding.ItemActorBinding
import com.my.movie_details.entity.MovieEntity
import com.my.resources.extensions.load
import com.xwray.groupie.viewbinding.BindableItem

class ActorItem(
    private val content: MovieEntity.Actor,
    private val onItemClicked: (MovieEntity.Actor) -> Unit
) : BindableItem<ItemActorBinding>() {

    override fun getLayout(): Int = R.layout.item_actor

    override fun initializeViewBinding(view: View) = ItemActorBinding.bind(view)

    override fun bind(viewBinding: ItemActorBinding, position: Int) {
        viewBinding.actorName.text = content.name
        viewBinding.actorPreview.load(content.previewId)
        viewBinding.root.setOnClickListener { onItemClicked(content) }
    }
}