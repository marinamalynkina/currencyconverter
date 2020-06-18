package com.malynkina.core.feature.domain

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

abstract class RxFlowableUseCase<Parameters, Result> {
    private var disposable: Disposable? = null

    abstract fun buildFlowable(params: Parameters? = null): Flowable<Result>

    operator fun invoke(
        params: Parameters?,
        onComplete: (() -> Unit)? = null,
        onNext: ((Result) -> Unit)? = null,
        onError: ((ExtendedThrowable) -> Unit)? = null
    ) {
        cancel()
        val observer = object : ResourceSubscriber<Result>() {

            override fun onComplete() {
                onComplete?.invoke()
            }

            override fun onNext(t: Result) {
                onNext?.invoke(t)
            }

            override fun onError(throwable: Throwable) {
                onError?.invoke(ExtendedThrowable(error = throwable))
            }
        }
        this.disposable = observer
        return buildFlowable(params)
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
