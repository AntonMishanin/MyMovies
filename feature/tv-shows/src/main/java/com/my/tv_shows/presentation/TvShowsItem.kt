package com.my.tv_shows.presentation

import android.view.View
import com.my.tv_shows.R
import com.my.tv_shows.databinding.ItemTvShowsBinding
import com.my.tv_shows.entity.TvShowsEntity
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class TvShowsItem(
    private val content: TvShowsEntity,
    private val onItemClicked: (TvShowsEntity) -> Unit
) : BindableItem<ItemTvShowsBinding>() {

    override fun getLayout(): Int = R.layout.item_tv_shows

    override fun initializeViewBinding(view: View) = ItemTvShowsBinding.bind(view)

    override fun bind(viewBinding: ItemTvShowsBinding, position: Int) {
        viewBinding.title.text = content.title
        viewBinding.rating.rating = content.rating
        Picasso.get().load(content.imagePath).into(viewBinding.imagePreview)

        viewBinding.root.setOnClickListener { onItemClicked(content) }
    }
}