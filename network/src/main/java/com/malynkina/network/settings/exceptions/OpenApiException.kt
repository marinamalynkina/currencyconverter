package com.malynkina.network.settings.exceptions

import android.content.Context
import android.text.TextUtils
import org.json.JSONObject
import java.io.IOException

class OpenApiException(
    var error: ErrorType? = null,
    var errorText: String? = null
) : IOException() {

    interface IErrorType {
        fun getErrorText(context: Context, ex: OpenApiException): String
    }

    enum class ErrorType(
        val errorCode: Int
    ) : IErrorType {
        /**
         * **HTTP status code:** 400
         */
        INVALID_PARAMETER(400) {
            override fun getErrorText(context: Context, ex: OpenApiException): String {
                return "INVALID_PARAMETER"
            }
        },

        /**
         * **HTTP status code:** 404
         */
        REQUEST_NOT_FOUND(404) {
            override fun getErrorText(context: Context, ex: OpenApiException): String {
                return "REQUEST_NOT_FOUND"
            }
        },

        UNKNOWN(0) {
            override fun getErrorText(context: Context, ex: OpenApiException): String {
                return "UNKNOWN"
            }
        };

        companion object {

            fun getType(errorCode: Int): ErrorType {
                values()?.forEach {
                    if (it.errorCode == errorCode) return it
                }
                return UNKNOWN
            }
        }
    }

}
