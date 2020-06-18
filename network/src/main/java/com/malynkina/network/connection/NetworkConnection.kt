package com.malynkina.network.connection

import android.content.Context
import com.malynkina.network.settings.interceptors.NetworkInterceptor
import com.malynkina.network.settings.interceptors.OpenApiExceptionsInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConnection private constructor(
    context: Context,
    host: String,
    logging: Boolean
) : IAPIConnection {

    override val retrofit: Retrofit

    init {

        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            readTimeout(30, TimeUnit.SECONDS)
            addInterceptor(NetworkInterceptor(context))
            addInterceptor(OpenApiExceptionsInterceptor())
            if (logging) {
                addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
        }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(host)
            .client(okHttpClientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())

        retrofit = retrofitBuilder.build()

    }

    class Builder(
        val context: Context? = null
    ) {
        var host: String? = null
        var logging = false

        fun host(host: String): Builder {
            this.host = host
            return this
        }

        fun logEnabled(enabled: Boolean): Builder {
            this.logging = enabled
            return this
        }

        fun build(): NetworkConnection {
            return NetworkConnection(
                context!!,
                host!!,
                logging!!
            )
        }
    }

}
