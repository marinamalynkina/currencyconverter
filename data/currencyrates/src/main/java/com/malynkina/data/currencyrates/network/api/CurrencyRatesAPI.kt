package com.malynkina.data.currencyrates.network.api

import com.malynkina.data.currencyrates.network.model.CyrrencyRatesDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRatesAPI {

    /**
     * Response:
    {
        "baseCurrency": "EUR",
        "rates": {
            "AUD": 1.6,
            "BGN": 1.96,
            "BRL": 4.184
        }
    }
     */
    @GET("latest")
    fun rates(@Query("base") currency: String?): Single<CyrrencyRatesDTO>
}