package com.my.movie_details.utils

import android.widget.CheckBox

fun CheckBox.setCheckedListener(isChecked: (Boolean) -> Unit) {
    this.setOnCheckedChangeListener { _, value ->
        isChecked(value)
    }
}