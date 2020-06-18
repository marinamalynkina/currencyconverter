package com.malynkina.core.feature.ui

import androidx.fragment.app.Fragment

interface ToolbarProcessing {
    fun setToolbarTitle(title: String)
}

fun Fragment.setToolbarTitle(title: String) {
    (activity as? ToolbarProcessing)?.setToolbarTitle(title)
}