package com.malynkina.core.feature.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.intervale.core.feature.StringResource
import com.malynkina.core.design.toast.toast

fun Fragment.defaultToastObserver(
    warning: Boolean = false
) = Observer<StringResource> { stringResource ->
    toast(stringResource)
}

fun Fragment.toast(stringResource: StringResource) {
    val context = context ?: return
    toast(context) {
        setMessage(stringResource.format(context))
    }
}
