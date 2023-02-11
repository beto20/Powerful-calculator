package com.alberto.powerful.calculator.ui.configuration

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.databinding.FragmentConfigurationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigurationFragment : Fragment(R.layout.fragment_configuration) {

    private lateinit var binding: FragmentConfigurationBinding
    private val viewModel: ConfigurationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfigurationBinding.bind(view)

        cleanCacheDataEvent()
        changeStyleModeEvent()
    }

    @SuppressLint("ResourceAsColor")
    private fun changeStyleModeEvent() = with(binding) {
        swLightMode.setOnClickListener {
            // TODO:: add sharepreferences in order to persist the background color to the other views
            if (swLightMode.isChecked) binding.frameLayout.setBackgroundColor(R.color.lightMode)
            if (!swLightMode.isChecked) binding.frameLayout.setBackgroundColor(R.color.darkMode)
        }
    }

    private fun cleanCacheDataEvent() = with(binding) {
        btnCleanMemory.setOnClickListener {
            viewModel.deleteAllRecords()
            viewModel.deleteAllRecordsCurrency()
        }
    }
}