package com.my.core.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(path: String) {
    Picasso.get()
        .load(path)
        .into(this)
}

fun ImageView.load(resId: Int) {
    Picasso.get()
        .load(resId)
        .into(this)
}