package com.malynkina.core.feature.util

import android.content.Context

fun Context.getDrawableId(name: String): Int {
    return resources.getIdentifier(name, "drawable", packageName)
}