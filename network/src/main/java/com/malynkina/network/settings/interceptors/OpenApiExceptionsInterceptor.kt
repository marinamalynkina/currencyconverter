package com.malynkina.network.settings.interceptors

import com.google.gson.Gson
import com.malynkina.network.settings.exceptions.OpenApiException
import okhttp3.Interceptor
import okhttp3.Response

import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

open class OpenApiExceptionsInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code >= 400) {
            throwError(response)
        }
        return response
    }

    @Throws(IOException::class)
    fun throwError(response: Response) {
        val responseBody = response.body
        val source = responseBody!!.source()
        source.request(java.lang.Long.MAX_VALUE)
        val buffer = source.buffer

        val contentType = responseBody.contentType()
        val charset = contentType?.charset(Charset.forName("UTF-8"))

        if (responseBody.contentLength() != 0L) {
            val responseString = buffer.clone().readString(charset!!)

            val errorType = OpenApiException.ErrorType.getType(response.code)

            throw OpenApiException(
                error = errorType,
                errorText = responseString)
        } else {
            throw OpenApiException(OpenApiException.ErrorType.UNKNOWN)
        }
    }
}