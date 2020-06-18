package com.malynkina.feature.currencyconverter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.intervale.core.feature.ui.defaultErrorObserver
import com.malynkina.core.feature.ui.setToolbarTitle
import com.malynkina.feature.currencyconverter.R
import com.malynkina.feature.currencyconverter.databinding.FeatureCurrencyconverterFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CurrencyConverterFragment : DaggerFragment(R.layout.feature_currencyconverter_fragment) {

    @Inject
    internal lateinit var viewModel: CurrencyConverterViewModel

    private lateinit var currencyAdapter: CurrencyConverterAdapter

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var binding: FeatureCurrencyconverterFragmentBinding? = null

    private var afterPause: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setToolbarTitle(getString(R.string.feature_currencyconverter_toolbar_title))
        viewModel.error.observe(viewLifecycleOwner, defaultErrorObserver())

        currencyAdapter = CurrencyConverterAdapter(viewModel)

        binding = FeatureCurrencyconverterFragmentBinding.bind(view)
        binding?.run {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            recyclerview.adapter = currencyAdapter
            viewModel.rates.observe(viewLifecycleOwner, Observer {
                currencyAdapter.apply {
                    submitList(it){
                        if (scrollToTop == true) {
                            recyclerview.scrollToPosition(0)
                            scrollToTop = false
                        }
                    }
                }})

            val itemAnimator = recyclerview.itemAnimator
            if (itemAnimator is SimpleItemAnimator) {
                itemAnimator.supportsChangeAnimations = false
            }
        }

        if (savedInstanceState?.containsKey("afterPause") == true)
            afterPause = savedInstanceState?.getBoolean("afterPause")
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdating()
        viewModel.saveCachedValue(currencyAdapter.baseValue)
        afterPause = true
    }

    override fun onResume() {
        super.onResume()
        if (afterPause) viewModel.startUpdating()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("afterPause", true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}