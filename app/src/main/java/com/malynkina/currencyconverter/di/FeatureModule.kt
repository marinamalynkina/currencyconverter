package com.malynkina.currencyconverter.di

import com.malynkina.feature.currencyconverter.ConverterFeatureModule
import dagger.Module

@Module(
    includes = [
        ConverterFeatureModule::class
    ]
)
class FeatureModule