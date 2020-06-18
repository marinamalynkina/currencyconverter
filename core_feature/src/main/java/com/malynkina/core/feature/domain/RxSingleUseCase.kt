package com.malynkina.core.feature.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class RxSingleUseCase<Parameters, Result> {
    private var disposable: Disposable? = null

    abstract fun buildSingle(params: Parameters): Single<Result>

    operator fun invoke(
        params: Parameters,
        onSuccess: ((Result) -> Unit)? = null,
        onError: ((ExtendedThrowable) -> Unit)? = null
    ) {
        cancel()
        val observer = object : DisposableSingleObserver<Result>() {
            override fun onSuccess(result: Result) {
                onSuccess?.invoke(result)
            }

            override fun onError(throwable: Throwable) {
                onError?.invoke(ExtendedThrowable(error = throwable))
            }
        }
        this.disposable = observer
        buildSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun cancel() {
        disposable?.run {
            if (!isDisposed) dispose()
        }
    }

}
