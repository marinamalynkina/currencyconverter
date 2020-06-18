package com.malynkina.data.currencyrates.model

import java.math.BigDecimal

interface CyrrencyRatesModel {
    val timeAdded: Long?
    val baseCurrency: String
    val rates: Map<String, BigDecimal>
}