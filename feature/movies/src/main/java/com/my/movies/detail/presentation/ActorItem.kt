package com.my.movies.detail.presentation

import android.view.View
import com.my.core.extensions.load
import com.my.movies.R
import com.my.movies.databinding.ItemActorBinding
import com.my.movies.detail.entity.MovieEntity
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