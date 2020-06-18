package com.intervale.core.feature

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class StringResource {

    abstract fun format(context: Context): String

    class Text(val text: String): StringResource() {
        override fun format(context: Context): String = text
    }

    class ResId(@StringRes val resId: Int, vararg args: Any): StringResource() {
        private val arguments = args
        override fun format(context: Context): String = context.getString(resId, *arguments)
    }
}

fun text(value: String) = StringResource.Text(value)
fun resId(@StringRes resId: Int, vararg args: Any) = StringResource.ResId(resId, *args)