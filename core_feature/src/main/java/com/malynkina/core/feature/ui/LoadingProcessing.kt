package com.malynkina.core.feature.ui

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.malynkina.core.design.dialog.LoadingDialogFragment

fun Fragment.defaultLoadingObserver() = Observer<Boolean> { isLoading ->
    when (isLoading) {
        true -> showLoadingDialog()
        else -> hideLoadingDialog()
    }
}

fun Fragment.showLoadingDialog() {
    if (childFragmentManager.findFragmentByTag(LoadingDialogFragment::class.java.simpleName) == null) {
        LoadingDialogFragment()
            .show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }
}

fun Fragment.hideLoadingDialog() {
    childFragmentManager.findFragmentByTag(LoadingDialogFragment::class.java.simpleName)?.run {
        when (this) {
            is DialogFragment -> dismiss()
        }
    }
}
