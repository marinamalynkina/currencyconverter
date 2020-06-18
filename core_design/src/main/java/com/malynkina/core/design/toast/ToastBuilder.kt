package com.malynkina.core.design.toast

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes

class ToastBuilder(private val context: Context) {
    private var message: String? = null
    private var duration = Toast.LENGTH_SHORT
    private var error = false

    fun setMessage(message: String?) = apply {
        this.message = message
    }

    fun setMessage(@StringRes messageRes: Int) = apply {
        message = context.getString(messageRes)
    }

    fun setDuration(duration: Int) = apply {
        this.duration = duration
    }

    fun setErrorStatus() = apply {
        error = true
    }

    fun buildToast(): Toast {

        val toast = Toast.makeText(context, message, duration)
        toast.setGravity(Gravity.TOP, 0, 0)
        return toast
    }

    fun show() {
        buildToast().show()
    }

}

fun toast(context: Context, buildDialog: ToastBuilder.() -> Unit)
        = ToastBuilder(context).apply(buildDialog).show()

var ToastBuilder.message: String?
    get() = throw unsupportedGetProperty("message")
    set(value) = this.run { setMessage(value) }

var ToastBuilder.duration: Int
    get() = throw unsupportedGetProperty("duration")
    set(value) = this.run { setDuration(value) }

fun ToastBuilder.shortToast() = setDuration(Toast.LENGTH_SHORT)
fun ToastBuilder.longToast() = setDuration(Toast.LENGTH_LONG)

fun ToastBuilder.error() = setErrorStatus()

private fun unsupportedGetProperty(propertyName: String)
    = UnsupportedOperationException("Get method for property \"InfoDialogBuilder.$propertyName\" not allowed")