package com.malynkina.feature.currencyconverter.domain

import com.malynkina.data.currencyrates.repository.CurrencyRatesRepositoryImpl
import com.malynkina.feature.currencyconverter.domain.interactor.CurrencyInteractor
import com.malynkina.feature.currencyconverter.domain.usecase.GetCachedRatesUseCase
import com.malynkina.feature.currencyconverter.domain.usecase.GetRatesUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCurrencyInteractor(
        currencyRatesRepository: CurrencyRatesRepositoryImpl
    ): CurrencyInteractor =
        CurrencyInteractor(
            getCurrencyRates = GetRatesUseCase(currencyRatesRepository),
            getCachedRates = GetCachedRatesUseCase(currencyRatesRepository)
        )
}