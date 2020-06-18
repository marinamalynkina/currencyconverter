package com.malynkina.core.feature.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.malynkina.core.feature.R
import com.malynkina.core.feature.util.getCountryCode
import com.malynkina.core.feature.util.getDrawableId


object ViewBinding {

    @BindingAdapter("currencyFlag")
    @JvmStatic
    fun ImageView.setCurrencyFlag(currencyCode: String?) {
        // find drawable by currency
        var id = 0
        currencyCode?.let {
            id = context.getDrawableId("ic_currency_${currencyCode?.toLowerCase()}")
            if (id == 0) {
                // find drawable by country code
                id = context.getDrawableId("ic_countryflag_${getCountryCode(currencyCode)?.toLowerCase()}")
            }
        }
        if (id == 0) id = R.drawable.ic_flag_placeholder
        setImageResource(id)
    }

    @BindingAdapter("goneUnless")
    @JvmStatic fun goneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

}