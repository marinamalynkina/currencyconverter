package com.malynkina.currencyconverter.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malynkina.network.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication
import javax.inject.Provider

@Module(
    includes = [
        ActivityModule::class,
        DataModule::class,
        FeatureModule::class
    ]
)
class AppModule {

    @Provides
    fun provideContext(application: DaggerApplication): Context = application

    @Provides
    fun provideNetworkConfig(
    ): NetworkConfig = NetworkConfig(
        openApiHost = "https://hiring.revolut.codes/api/android/"
    )

    @Provides
    fun provideViewModelProviderFactory(
        providers: Map<Class<*>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return requireNotNull(providers[modelClass]).get() as T
            }
        }
    }

}
