package com.intervale.core.feature.data.localization

import android.content.Context
import android.content.SharedPreferences

class CurrencyConverterStorageImpl(
    context: Context
) : CurrencyConverterStorage {

    val DEFAULT_BASE_CURRENCY = "EUR"
    val BASE_CURRENCY = "baseCurrency"
    val DEFAULT_BASE_VALUE = 10000L
    val BASE_VALUE = "baseValue"

    private val storage: SharedPreferences =
        context.getSharedPreferences("feature_currency_converter_storage", Context.MODE_PRIVATE)

    override fun getBaseCurrency(): String {
        return (storage.getString(BASE_CURRENCY, DEFAULT_BASE_CURRENCY) ?: DEFAULT_BASE_CURRENCY).toUpperCase()
    }

    override fun setBaseCurrency(currency: String) {
        storage.edit()
            .putString(BASE_CURRENCY, currency)
            .apply()
    }

    override fun getBaseValue(): Long {
        return storage.getLong(BASE_VALUE, DEFAULT_BASE_VALUE)
    }

    override fun setBaseValue(value: Long) {
        storage.edit()
            .putLong(BASE_VALUE, value)
            .apply()
    }

}
