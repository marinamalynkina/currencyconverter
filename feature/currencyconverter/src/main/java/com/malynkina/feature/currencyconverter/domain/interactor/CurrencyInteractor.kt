package com.malynkina.feature.currencyconverter.domain.interactor

import com.malynkina.feature.currencyconverter.domain.usecase.GetCachedRatesUseCase
import com.malynkina.feature.currencyconverter.domain.usecase.GetRatesUseCase

class CurrencyInteractor (
    val getCurrencyRates: GetRatesUseCase,
    val getCachedRates: GetCachedRatesUseCase
)