package com.malynkina.feature.currencyconverter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.intervale.core.feature.data.localization.CurrencyConverterStorage
import com.malynkina.feature.currencyconverter.domain.interactor.CurrencyInteractor
import com.malynkina.network.observer.NetworkAliveObserver
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class CurrencyConverterModule {

    @ContributesAndroidInjector(modules = [ProvideModule::class])
    abstract fun contribute(): CurrencyConverterFragment

    @Module
    class ProvideModule {

        @Provides
        fun inject(
            target: CurrencyConverterFragment,
            factory: ViewModelProvider.Factory
        ) = ViewModelProviders.of(target, factory).get(CurrencyConverterViewModel::class.java)

        @Provides
        @IntoMap
        @ClassKey(CurrencyConverterViewModel::class)
        fun provide(
            currencyInteractor: CurrencyInteractor,
            networkAliveObserver: NetworkAliveObserver,
            currencyConverterStorage: CurrencyConverterStorage
        ): ViewModel =
            CurrencyConverterViewModel(
                interactor = currencyInteractor,
                networkAliveObserver = networkAliveObserver,
                currencyConverterStorage = currencyConverterStorage
            )
    }
}
