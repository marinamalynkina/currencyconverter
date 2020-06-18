package com.malynkina.data.currencyrates.network.model

import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import java.math.BigDecimal

data class CyrrencyRatesDTO(
    override val baseCurrency: String,
    override val rates: Map<String, BigDecimal>,
    override val timeAdded: Long? = null
) : CyrrencyRatesModel