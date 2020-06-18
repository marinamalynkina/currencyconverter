package com.malynkina.feature.currencyconverter.domain.usecase

import com.malynkina.core.feature.domain.RxSingleUseCase
import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import com.malynkina.data.currencyrates.repository.CurrencyRatesRepositoryImpl
import io.reactivex.Single

class GetCachedRatesUseCase(
    private val currencyRatesRepository: CurrencyRatesRepositoryImpl
) : RxSingleUseCase<String?, CyrrencyRatesModel>() {

    override fun buildSingle(currency: String?): Single<CyrrencyRatesModel> {
        return currencyRatesRepository.getCachedRated(currency)
    }

}