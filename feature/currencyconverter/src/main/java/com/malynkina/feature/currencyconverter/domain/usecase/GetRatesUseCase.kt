package com.malynkina.feature.currencyconverter.domain.usecase

import com.malynkina.core.feature.domain.RxFlowableUseCase
import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import com.malynkina.data.currencyrates.repository.CurrencyRatesRepositoryImpl
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class GetRatesUseCase(
    private val currencyRatesRepository: CurrencyRatesRepositoryImpl
) : RxFlowableUseCase<String?, CyrrencyRatesModel>() {

    override fun buildFlowable(currency: String?): Flowable<CyrrencyRatesModel> {
        return Flowable.interval(1, TimeUnit.SECONDS)
                    .startWith(0)
                    .flatMap {
                        currencyRatesRepository.getCurrencyRates(currency)
                    }
    }

}