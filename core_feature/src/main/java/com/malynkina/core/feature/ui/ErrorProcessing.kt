package com.intervale.core.feature.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.intervale.core.feature.StringResource
import com.malynkina.core.feature.BuildConfig
import com.malynkina.core.feature.domain.ExtendedThrowable
import com.malynkina.core.feature.ui.hideCommonMessage
import com.malynkina.core.feature.ui.showCommonMessage
import com.malynkina.core.feature.ui.toast
import com.malynkina.network.settings.exceptions.NetworkException
import com.malynkina.network.settings.exceptions.OpenApiException

fun Fragment.defaultErrorObserver() = Observer<ExtendedThrowable?> { throwable ->
    if (BuildConfig.DEBUG) Log.w("ErrorProcessing", "handleError:", throwable?.error)
    throwable?.run {
        when (throwable.error) {
            is OpenApiException -> toast(StringResource.Text(throwable.error.errorText ?: ""))
            is NetworkException -> showCommonMessage(throwable.error.errorType.errorTextId(), throwable.additionalMessage?.format(context!!))
        }
    } ?: hideCommonMessage()

}