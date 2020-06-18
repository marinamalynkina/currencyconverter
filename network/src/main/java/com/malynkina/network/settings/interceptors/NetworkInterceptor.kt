package com.malynkina.network.settings.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.malynkina.network.settings.exceptions.NetworkException
import com.malynkina.network.settings.exceptions.OpenApiException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    val appContext: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (isNetworkConnected()) {
            try {
                chain.proceed(chain.request())
            } catch (e: Exception) {
                if (e is OpenApiException) {
                    throw e
                } else {
                    e.printStackTrace()
                    val err = NetworkException.ErrorType.defineByExceptionClassName(e)
                    throw NetworkException(err)
                }
            }

        } else {
            throw NetworkException()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}