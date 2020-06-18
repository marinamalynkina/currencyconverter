package com.malynkina.core.feature.domain

import com.intervale.core.feature.StringResource

class ExtendedThrowable(
    var additionalMessage: StringResource? = null,
    open val error: Throwable?
)