package com.malynkina.feature.currencyconverter

import com.malynkina.feature.currencyconverter.data.DataModule
import com.malynkina.feature.currencyconverter.domain.DomainModule
import com.malynkina.feature.currencyconverter.ui.CurrencyConverterModule
import dagger.Module

@Module(
    includes = [
        DomainModule::class,
        DataModule::class,
        CurrencyConverterModule::class
    ]

)
class ConverterFeatureModule {
}