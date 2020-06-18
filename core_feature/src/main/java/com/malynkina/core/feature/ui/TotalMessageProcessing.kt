package com.malynkina.core.feature.ui

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

interface TotalMessageProcessing {
    fun showCommonMessage(title: String?)
    fun hideCommonMessage()
}

fun Fragment.showCommonMessage(@StringRes title: Int, message: String?) {
    showCommonMessage(context?.getString(title), message)
}

fun Fragment.showCommonMessage(title: String?, message: String?) {
    (activity as? TotalMessageProcessing)?.showCommonMessage("${title}\n${message}")
}

fun Fragment.hideCommonMessage() {
    (activity as? TotalMessageProcessing)?.hideCommonMessage()
}