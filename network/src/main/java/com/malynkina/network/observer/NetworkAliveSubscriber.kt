package com.malynkina.network.observer

class NetworkAliveSubscriber(
    var name: String,
    var action: () -> Unit
) {
    fun callAction() {
        action?.invoke()
    }

}