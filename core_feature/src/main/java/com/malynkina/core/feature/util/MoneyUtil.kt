package com.intervale.core.feature.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun String?.toMinorMoney(): Long {
    if (this == null) return 0L

    val splittedByComma = split('.')
    if (splittedByComma.isEmpty()) return 0L

    var value = (splittedByComma[0].toLongOrNull() ?: 0L) * 100

    if (splittedByComma.size == 1) return value

    value += if (splittedByComma[1].length == 1) (splittedByComma[1].toLongOrNull() ?: 0L) * 10
    else splittedByComma[1].toLongOrNull() ?: 0

    return value
}

fun BigDecimal?.toFormattedMoney(pattern: String = "#0.00"): String {
    val value = this ?: BigDecimal.ZERO
    val asDouble: BigDecimal = value.divide(BigDecimal(100))

    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.decimalSeparator = '.'

    val df = DecimalFormat(pattern, symbols).apply {
        minimumFractionDigits = 0
    }

    var newValue = DecimalFormat(pattern, symbols).format(asDouble)
    if (newValue.endsWith(".00")) newValue = newValue.removeSuffix(".00")
    return newValue
}

