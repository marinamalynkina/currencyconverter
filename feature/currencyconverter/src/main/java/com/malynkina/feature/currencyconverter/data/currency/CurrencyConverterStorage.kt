package com.intervale.core.feature.data.localization

interface CurrencyConverterStorage {
    fun getBaseCurrency(): String
    fun setBaseCurrency(currency: String)
    fun getBaseValue(): Long
    fun setBaseValue(value: Long)
}
