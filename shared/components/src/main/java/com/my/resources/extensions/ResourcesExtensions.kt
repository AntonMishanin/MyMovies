package com.my.resources.extensions

import android.content.Context

fun Context.getStringByResName(resName: String): String {
    val id = this.resources.getIdentifier(resName, "string", this.packageName)
    return this.resources.getString(id)
}