package com.malynkina.feature.currencyconverter.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intervale.core.feature.util.toFormattedMoney
import com.intervale.core.feature.util.toMinorMoney
import com.malynkina.core.feature.util.SimpleTextWatcher
import com.malynkina.feature.currencyconverter.databinding.FeatureCurrencyconverterItemBinding
import com.malynkina.feature.currencyconverter.model.CurrencyItemModel
import com.malynkina.feature.currencyconverter.util.ViewBinding
import java.math.BigDecimal

class CurrencyConverterAdapter (
    var commonViewModel: CurrencyConverterViewModel,
    diffCallback: DiffUtil.ItemCallback<CurrencyItemModel> = DiffCallback()
) : ListAdapter<CurrencyItemModel, CurrencyConverterAdapter.ViewHolder>(diffCallback) {

    var scrollToTop: Boolean = false

    var baseValue: Long = commonViewModel.getCachedValue()
        set(newValue: Long) {
            if (newValue == 0L) field = 1L
            else field = newValue
        }

    var attachedHolders = HashMap<String, ViewHolder>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FeatureCurrencyconverterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.setItem(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        val bundle = payloads.first()
        if (bundle is Bundle) {
            if (bundle.containsKey("rate")) {
                holder.updateRate(bundle.getSerializable("rate") as BigDecimal)
            }
        }
    }

    private fun sortOnClick(item: CurrencyItemModel, newValue: Long?) {
        if (item.code != commonViewModel.baseCurrency.value) {
            baseValue = newValue ?: 100
            commonViewModel.baseCurrency.value = item.code
            scrollToTop = true
        }
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.binding.currencyCode?.let { key ->
            attachedHolders.put(key, holder)
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        attachedHolders.remove(holder.binding.currencyCode)
    }

    private fun updateValueForAllAttached() {
        attachedHolders.keys.forEach {
            attachedHolders.get(it)?.run {
                if (!isBaseCurrency) updateRate()
            }
        }
    }

    inner class ViewHolder(
        val binding: FeatureCurrencyconverterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        var rate: BigDecimal = BigDecimal.ZERO
        var convertedValue: MutableLiveData<String> = MutableLiveData<String>("")
        val isBaseElement: Boolean
            get() = adapterPosition == 0
        val isBaseCurrency: Boolean
            get() = binding.currencyCode == commonViewModel.baseCurrency.value

        fun setItem(item: CurrencyItemModel) {
            // set this observer only for base item
            convertedValue.observe(binding.root.context as LifecycleOwner, Observer {
                if (isBaseElement && isBaseCurrency) {
                    baseValue = it.toMinorMoney()
                    // update all visible values after this
                    updateValueForAllAttached()
                }
            })
            binding.currencyRateView.addTextChangedListener(object : SimpleTextWatcher() {
                var changedvalue: String? = null
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (isBaseElement && isBaseCurrency) {
                        if (ViewBinding.pattern?.matcher(s)?.matches() != true) {
                            changedvalue = s.removeRange(start, start + count).toString()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if (isBaseElement && isBaseCurrency) {
                        if (changedvalue != null ) {
                            convertedValue.value = changedvalue
                            changedvalue = null
                        }
                    }
                }
            })
            binding.currencyRateView.setOnFocusChangeListener { v, hasFocus ->
                if (!isBaseElement && hasFocus) {
                    sortOnClick(currentList.get(adapterPosition), convertedValue.value.toMinorMoney())
                }
            }
            binding.currencyView.setOnClickListener {
                binding.currencyRateView.requestFocus()
            }

            binding.apply {
                lifecycleOwner = binding.root.context as LifecycleOwner
                currencyCode = item.code
                convertedValue = this@ViewHolder.convertedValue
            }
            updateRate(item.rate)
        }

        fun updateRate(newRate: BigDecimal? = null) {
            newRate?.let { rate = newRate }
            convertedValue.value = (rate*BigDecimal(baseValue)).toFormattedMoney()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CurrencyItemModel>() {
        override fun areItemsTheSame(oldItem: CurrencyItemModel, newItem: CurrencyItemModel): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: CurrencyItemModel, newItem: CurrencyItemModel): Boolean {
            return oldItem.rate == newItem.rate
        }

        override fun getChangePayload(oldItem: CurrencyItemModel, newItem: CurrencyItemModel): Any? {
            return if (!areContentsTheSame(oldItem, newItem)) {
                bundleOf ("rate" to newItem.rate)
            } else null
        }
    }
}