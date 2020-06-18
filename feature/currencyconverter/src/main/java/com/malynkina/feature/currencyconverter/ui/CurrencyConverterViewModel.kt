package com.malynkina.feature.currencyconverter.ui

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intervale.core.feature.StringResource
import com.intervale.core.feature.data.localization.CurrencyConverterStorage
import com.malynkina.core.feature.domain.ExtendedThrowable
import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import com.malynkina.feature.currencyconverter.R
import com.malynkina.feature.currencyconverter.domain.interactor.CurrencyInteractor
import com.malynkina.feature.currencyconverter.model.CurrencyItemModel
import com.malynkina.network.observer.NetworkAliveObserver
import com.malynkina.network.observer.NetworkAliveSubscriber
import com.malynkina.network.settings.exceptions.NetworkException
import java.math.BigDecimal

class CurrencyConverterViewModel (
    private val networkAliveObserver: NetworkAliveObserver,
    private val interactor: CurrencyInteractor,
    private val currencyConverterStorage: CurrencyConverterStorage
) : ViewModel() {

    val error = MutableLiveData<ExtendedThrowable?>()

    val isSwipeLoading = MutableLiveData<Boolean>(false)
    fun startSwipeLoading() { if ( isSwipeLoading.value != true ) isSwipeLoading.value = true}
    fun stopSwipeLoading() { if ( isSwipeLoading.value != false ) isSwipeLoading.value = false}

    val baseCurrency: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = currencyConverterStorage.getBaseCurrency()
    }

    var networkAliveSubscriber: NetworkAliveSubscriber? = null

    val rates =  MediatorLiveData<List<CurrencyItemModel>>().apply {
        addSource(baseCurrency) { currency ->
            currencyConverterStorage.setBaseCurrency(currency)
            startUpdating(currency)
        }
    }

    fun getCachedValue(): Long {
        return currencyConverterStorage.getBaseValue()
    }

    fun saveCachedValue(value: Long) {
        currencyConverterStorage.setBaseValue(value)
    }

    fun startUpdatingOnSwipe() {
        startSwipeLoading()
        startUpdating()
    }

    fun startUpdating(currency: String? = baseCurrency.value) {
        interactor.getCurrencyRates.cancel()
        interactor.getCurrencyRates.invoke(
            params = currency,
            onNext = {
                rates.value = it.toItemModelList()
                error.value = null
                stopSwipeLoading()
            },
            onError = { throwable ->
                stopSwipeLoading()
                interactor.getCurrencyRates.cancel()
                handleError(throwable, currency)
            }
        )
    }

    fun getFromCache(currency: String? = baseCurrency.value) {
        interactor.getCachedRates.cancel()
        interactor.getCachedRates.invoke(
            params = currency,
            onSuccess = {
                rates.value = it.toItemModelList()
                error.value?.let {
                    handleError(it, currency)
                }
                stopSwipeLoading()
            },
            onError = { throwable ->
                interactor.getCachedRates.cancel()
            }
        )
    }

    fun stopUpdating() {
        interactor.getCurrencyRates.cancel()
        networkAliveSubscriber?.let {
            networkAliveObserver.removeSubscriber(it)
        }

    }

    private fun CyrrencyRatesModel.toItemModelList(): List<CurrencyItemModel> =
        mutableListOf<CurrencyItemModel>().apply {
            add(CurrencyItemModel(baseCurrency, BigDecimal.ONE))
            rates.forEach { entry ->
                add(CurrencyItemModel(entry.key, entry.value))
            }
        }

    private fun convertExistList(currency: String): List<CurrencyItemModel> {
        return mutableListOf<CurrencyItemModel>().apply {
            val newBase = rates.value?.find { it.code == currency }
            add(CurrencyItemModel(currency, BigDecimal.ONE))
            rates.value?.minus(newBase)?.sortedBy { it?.code }?.forEach {
                add(CurrencyItemModel(it!!.code, it.rate/(newBase?.rate ?: BigDecimal.ONE)))
            }
        }
    }

    fun handleError(throwable: ExtendedThrowable, currency: String?) {
        Log.i("adapter","throwable = ${throwable.error?.message} cause ${throwable.error?.cause}")
        if (rates.value != null) {
            error.value = null
            throwable.additionalMessage = StringResource.ResId(R.string.feature_currencyconverter_outdated)
        } else {
            getFromCache()
            throwable.additionalMessage = StringResource.ResId(R.string.feature_currencyconverter_swipedown)
        }
        error.value = throwable

        if (throwable.error is NetworkException) {
            networkAliveSubscriber = NetworkAliveSubscriber("getCurrencyRates", { startUpdating() } )
            networkAliveObserver.addSubscriber(networkAliveSubscriber!!)
        }

        currency?.let {
            if (rates.value != null)
                rates.value = convertExistList(currency)
        }
    }
}