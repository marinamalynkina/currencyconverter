package com.malynkina.network.observer

interface NetworkAliveObserver {

    fun addSubscriber(subscriber: NetworkAliveSubscriber)

    fun removeSubscriber(subscriber: NetworkAliveSubscriber)

}