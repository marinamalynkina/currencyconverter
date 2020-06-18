package com.malynkina.feature.currencyconverter.data

import android.content.Context
import com.intervale.core.feature.data.localization.CurrencyConverterStorage
import com.intervale.core.feature.data.localization.CurrencyConverterStorageImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideLocalizationStorage(
        context: Context
    ): CurrencyConverterStorage = CurrencyConverterStorageImpl(context)
}