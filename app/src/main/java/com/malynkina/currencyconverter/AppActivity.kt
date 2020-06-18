package com.malynkina.currencyconverter

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.malynkina.core.feature.ui.ToolbarProcessing
import com.malynkina.core.feature.ui.TotalMessageProcessing
import com.malynkina.currencyconverter.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class AppActivity : DaggerAppCompatActivity(), ToolbarProcessing, TotalMessageProcessing {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun setToolbarTitle(title: String) {
        binding.toolbarTitle.text = title
    }

    override fun showCommonMessage(title: String?) {
        title?.let {
            binding.totalMessage.apply {
                visibility = View.VISIBLE
                setText(title)
            }
        }
    }

    override fun hideCommonMessage() {
        binding.totalMessage.apply {
            if (visibility == View.VISIBLE) visibility = View.GONE
        }
    }
}