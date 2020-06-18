package com.malynkina.core.feature.util

import android.text.Editable
import android.text.TextWatcher

open class SimpleTextWatcher : TextWatcher {
    open override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    open override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
    }

    open override fun afterTextChanged(s: Editable?) {}
}