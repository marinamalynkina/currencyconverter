package com.malynkina.data.currencyrates.repository

import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import io.reactivex.Flowable
import io.reactivex.Single

interface CurrencyRatesRepository {
    fun getCachedRated(currency: String? = null): Single<CyrrencyRatesModel>
    fun getCurrencyRates(currency: String? = null): Flowable<CyrrencyRatesModel>
}