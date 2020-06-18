package com.malynkina.network.observer

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkAliveObserverImpl(
    val connectivityManager: ConnectivityManager
) : NetworkAliveObserver{

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    private val subscribers = HashMap<String, NetworkAliveSubscriber>()

    override fun addSubscriber(subscriber: NetworkAliveSubscriber) {
        if (subscribers.size == 0) registerActionOnNetworkAvailable()
        subscribers.put(subscriber.name, subscriber)
    }

    override fun removeSubscriber(subscriber: NetworkAliveSubscriber) {
        subscribers.remove(subscriber.name)
    }

    private fun notifySubscribers() {
        subscribers.keys.forEach {
            subscribers.get(it)?.apply {
                subscribers.remove(it)
                callAction()
            }
        }
    }

    private fun createNetworkCallback() {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                unregisterActionOnNetworkAvailable()
                notifySubscribers()
            }
        }
    }

    private fun registerActionOnNetworkAvailable() {
        if (networkCallback == null) {
            createNetworkCallback()
            val networkRequest = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build()
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback!!)
        }
    }

    private fun unregisterActionOnNetworkAvailable() {
        connectivityManager.unregisterNetworkCallback(networkCallback!!)
        networkCallback = null
    }

}