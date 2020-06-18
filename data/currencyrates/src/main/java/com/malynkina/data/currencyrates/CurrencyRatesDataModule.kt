package com.malynkina.data.currencyrates

import com.malynkina.data.currencyrates.db.dao.CurrencyRatesDAO
import com.malynkina.data.currencyrates.network.api.CurrencyRatesAPI
import com.malynkina.data.currencyrates.repository.CurrencyRatesRepositoryImpl
import com.malynkina.data.currencyrates.repository.DBRepository
import com.malynkina.network.connection.IAPIConnection
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyRatesDataModule {

    @Singleton
    @Provides
    fun provideCurrencyRatesAPI(connection: Lazy<IAPIConnection>): CurrencyRatesAPI =
        connection.get().retrofit.create(CurrencyRatesAPI::class.java)

    @Provides
    fun provideDbRepository(
        dao: CurrencyRatesDAO
    ) = DBRepository(dao)

    @Singleton
    @Provides
    fun provideCurrencyRatesRepository(
        api: CurrencyRatesAPI,
        db: DBRepository
    ) =
        CurrencyRatesRepositoryImpl(api, db)



}