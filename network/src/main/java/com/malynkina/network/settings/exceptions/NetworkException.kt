package com.malynkina.network.settings.exceptions

import com.malynkina.network.R
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException

class NetworkException constructor(
    val errorType: ErrorType
) : IOException() {

    constructor() : this(ErrorType.NO_CONNECTION) {}
    constructor(errorMsg: String) : this (
        ErrorType.defineType(
            errorMsg
        )
    )

    interface IErrorType {
        fun instanceOf(e: Exception): Boolean
        fun errorTextId(): Int
    }

    enum class ErrorType : IErrorType {
        NO_CONNECTION {
            override fun errorTextId(): Int = R.string.network_error__NO_CONNECTION
        },
        SOCKET_TIMEOUT("SocketTimeoutException") {
            override fun instanceOf(e: Exception): Boolean {
                return e is SocketTimeoutException
            }
            override fun errorTextId(): Int = R.string.network_error__SOCKET_TIMEOUT
        },
        SOCKET_CLOSED("SocketException") {
            override fun instanceOf(e: Exception): Boolean {
                return e is SocketException
            }
            override fun errorTextId(): Int = R.string.network_error__SOCKET_CLOSED
        },
        CONNECTION_REFUSED("ConnectException") {
            override fun instanceOf(e: Exception): Boolean {
                return e is ConnectException
            }
            override fun errorTextId(): Int = R.string.network_error__CONNECTION_REFUSED
        },
        SSL("SSLException") {
            override fun instanceOf(e: Exception): Boolean {
                return e is SSLException
            }
            override fun errorTextId(): Int = R.string.network_error__SSL
        },
        UNKNOWN_HOST("UnknownHostException") {
            override fun errorTextId(): Int = R.string.network_error__UNKNOWN_HOST
        },
        UNKNOWN{
            override fun errorTextId(): Int = R.string.network_error__UNKNOWN
        };

        private var exceptionClassName: String? = null

        private constructor() {
            this.exceptionClassName = this.name
        }

        private constructor(exceptionClassName: String) {
            this.exceptionClassName = exceptionClassName
        }

        override fun instanceOf(e: Exception): Boolean {
            return false
        }

        companion object {

            fun defineType(error: String): ErrorType {
                val errorType = valueOf(error)
                return errorType ?: UNKNOWN
            }

            fun defineByExceptionClassName(exception: Exception): ErrorType {
                var err = UNKNOWN
                for (i in values().indices) {
                    if (values().get(i).exceptionClassName == exception.javaClass.simpleName || values().get(i).instanceOf(exception)) {
                        err = values().get(i)
                        return err
                    }
                }
                return err
            }
        }

    }
}