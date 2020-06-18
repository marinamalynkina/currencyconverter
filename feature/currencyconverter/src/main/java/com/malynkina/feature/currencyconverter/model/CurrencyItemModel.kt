package com.malynkina.feature.currencyconverter.model

import java.math.BigDecimal

data class CurrencyItemModel(
    val code: String,
    val rate: BigDecimal
)