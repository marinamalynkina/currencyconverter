package com.malynkina.data.currencyrates.repository

import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import com.malynkina.data.currencyrates.network.api.CurrencyRatesAPI
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CurrencyRatesRepositoryImpl(
    private val api: CurrencyRatesAPI,
    private val dbRepository: DBRepository
) : CurrencyRatesRepository {

    override fun getCachedRated(currency: String?): Single<CyrrencyRatesModel> {
        return dbRepository.getRates(currency)
            .subscribeOn(Schedulers.io())
            .map { model -> model as CyrrencyRatesModel }
    }

    override fun getCurrencyRates(currency: String?): Flowable<CyrrencyRatesModel> {
        return getFromNetwork(currency)
            .doOnNext { dbRepository.updateRates(it) }
            .subscribeOn(Schedulers.io())

    }

    private fun getFromNetwork(currency: String? = null): Flowable<CyrrencyRatesModel> {
        return api.rates(currency)
            .map { model -> model as CyrrencyRatesModel }
            .toFlowable()

    }
}