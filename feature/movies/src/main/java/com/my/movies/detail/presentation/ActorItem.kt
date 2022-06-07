package com.my.movies.detail.presentation

import com.my.core.extensions.load
import com.my.core.presentation.BaseItem
import com.my.movies.R
import com.my.movies.databinding.ItemActorBinding
import com.my.movies.detail.entity.MovieEntity

class ActorItem(
    private val content: MovieEntity.Actor,
    private val onItemClicked: (MovieEntity.Actor) -> Unit
) : BaseItem<ItemActorBinding>(R.layout.item_actor, ItemActorBinding::bind) {

    override fun bind(viewBinding: ItemActorBinding, position: Int) {
        viewBinding.actorName.text = content.name
        viewBinding.actorPreview.load(content.previewId)
        viewBinding.root.setOnClickListener { onItemClicked(content) }
    }
}