package com.malynkina.core.feature.util

import java.util.*
import kotlin.collections.HashMap


fun getCurrencyName(currencyCode: String?): String? {
    return currencyCode?.let { Currency.getInstance(currencyCode).displayName } ?: ""
}

/*
 * this is not really correct map, because for come currency exist more than one country,
 * but it fit to search countryflag in resources, when you have separate resource for currencies
 */
fun createMapCurrencyCountry(): HashMap<String, Locale> = HashMap<String, Locale>().apply {
    for (country in Locale.getISOCountries()) {
        val locale = Locale("", country)

        put(Currency.getInstance(locale).currencyCode, locale)
    }
}

fun getCountryCode(currencyCode: String?): String? {
    for (country in Locale.getISOCountries()) {
        val locale = Locale("", country)

        Currency.getInstance(locale)?.let {
            if (it.currencyCode == currencyCode)
                return locale.country
        }

    }
    return null
}