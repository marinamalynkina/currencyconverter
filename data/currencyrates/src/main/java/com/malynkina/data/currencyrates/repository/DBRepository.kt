package com.malynkina.data.currencyrates.repository

import android.util.Log
import com.malynkina.data.currencyrates.db.dao.CurrencyRatesDAO
import com.malynkina.data.currencyrates.db.model.CurrencyRatesEntity
import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class DBRepository(
    private val dao: CurrencyRatesDAO
) {

    fun getRates(currency: String?): Single<CurrencyRatesEntity> {
        return dao.get(currency ?: "")
    }

    fun updateRates(currencyRates: CyrrencyRatesModel) {
        return dao.insert(CurrencyRatesEntity(
            baseCurrency = currencyRates.baseCurrency,
            rates = currencyRates.rates,
            timeAdded = System.currentTimeMillis()
        ))
    }

}