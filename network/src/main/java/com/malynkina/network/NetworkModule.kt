package com.malynkina.network

import android.content.Context
import android.net.ConnectivityManager
import com.malynkina.network.connection.IAPIConnection
import com.malynkina.network.connection.NetworkConnection
import com.malynkina.network.observer.NetworkAliveObserver
import com.malynkina.network.observer.NetworkAliveObserverImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkConnection(
        context: Context,
        standConfig: NetworkConfig
    ): IAPIConnection = NetworkConnection.Builder(context)
        .host(standConfig.openApiHost)
        .logEnabled(BuildConfig.DEBUG)
        .build()


    @Singleton
    @Provides
    fun provideNetworkAliveObserver(
        context: Context
    ): NetworkAliveObserver =
        NetworkAliveObserverImpl(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
}