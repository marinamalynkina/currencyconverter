package com.malynkina.feature.currencyconverter.util

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.util.regex.Pattern


object ViewBinding {

    val pattern = Pattern.compile("(^((0?|([1-9][0-9]{0,8}))(\\.[0-9]{0,2})?)\$)")

    @BindingAdapter(value = ["android:text"])
    @JvmStatic
    fun setText(view: EditText, newText: String?) {
        newText?.let {
            if (/*pattern?.matcher(newText)?.matches() != false &&*/ view.text.toString() != newText) {
                view.setText(newText)
            } else return
        }
        return
    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic
    fun getText(view: EditText): String? {
        return view.text.toString()
    }

    @BindingAdapter("onRefresh")
    @JvmStatic
    fun SwipeRefreshLayout.setRefresh(onRefreshListener: SwipeRefreshLayout.OnRefreshListener) {
        setOnRefreshListener(onRefreshListener)
    }

    @BindingAdapter("refreshing")
    @JvmStatic
    fun SwipeRefreshLayout.setIsRefreshing(refreshing: Boolean) {
        isRefreshing = refreshing
    }

}