package com.my.core.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.my.core.R
import com.squareup.picasso.Picasso

fun ImageView.load(path: String, @DrawableRes placeholderResId: Int = R.drawable.ic_avatar) {
    Picasso.get()
        .load(path)
        .placeholder(placeholderResId)
        .into(this)
}

fun ImageView.load(resId: Int, @DrawableRes placeholderResId: Int = R.drawable.ic_avatar) {
    Picasso.get()
        .load(resId)
        .placeholder(placeholderResId)
        .into(this)
}