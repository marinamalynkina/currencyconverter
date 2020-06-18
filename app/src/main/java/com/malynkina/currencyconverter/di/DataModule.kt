package com.malynkina.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.malynkina.currencyconverter.db.CurrencyConverterDatabase
import com.malynkina.data.currencyrates.CurrencyRatesDataModule
import com.malynkina.data.currencyrates.db.dao.CurrencyRatesDAO
import com.malynkina.network.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        CurrencyRatesDataModule::class
    ]
)
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        context: Context
    ): CurrencyConverterDatabase =
        Room.databaseBuilder(
            context,
            CurrencyConverterDatabase::class.java, "currency_converter_database"
        ).build()

    @Provides
    fun provideCurrencyRatesDAO(
        db: CurrencyConverterDatabase
    ): CurrencyRatesDAO = db.currencyRatesDAO()
}