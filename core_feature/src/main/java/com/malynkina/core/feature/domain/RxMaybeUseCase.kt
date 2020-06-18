package com.malynkina.core.feature.domain

import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

abstract class RxMaybeUseCase<Parameters, Result> {
    private var disposable: Disposable? = null

    abstract fun buildMaybe(params: Parameters): Maybe<Result>

    operator fun invoke(
        params: Parameters,
        onComplete: (() -> Unit)? = null,
        onSuccess: ((Result) -> Unit)? = null,
        onError: ((ExtendedThrowable) -> Unit)? = null
    ) {
        cancel()
        val observer = object : DisposableMaybeObserver<Result>() {

            override fun onComplete() {
                onComplete?.invoke()
            }

            override fun onSuccess(result: Result) {
                onSuccess?.invoke(result)
            }

            override fun onError(throwable: Throwable) {
                onError?.invoke(ExtendedThrowable(error = throwable))
            }
        }
        this.disposable = observer
        buildMaybe(params)
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
