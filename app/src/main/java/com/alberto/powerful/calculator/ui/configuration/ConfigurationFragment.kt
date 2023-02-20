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

        validateColorPreference()
        changeStyleModeEvent()
        cleanCacheDataEvent()
    }

    @SuppressLint("ResourceAsColor")
    private fun validateColorPreference() = with(binding) {
        val mode = viewModel.getSwitchModeValue()
        if (mode == "light") {
            swColorMode.isChecked = true
            binding.configurationFragment.setBackgroundColor(R.color.lightMode)
        } else {
            swColorMode.isChecked = false
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun changeStyleModeEvent() = with(binding) {
        swColorMode.setOnClickListener {
            var value = false
            if (swColorMode.isChecked) {
                value = true
                binding.configurationFragment.setBackgroundColor(R.color.lightMode)
                viewModel.saveSwitchModeValue("light")
            }
            if (!swColorMode.isChecked) {
                value = false
                binding.configurationFragment.setBackgroundColor(R.color.darkMode)
                viewModel.saveSwitchModeValue("dark")
            }
            swColorMode.isChecked = value
        }
    }

    private fun cleanCacheDataEvent() = with(binding) {
        btnCleanMemory.setOnClickListener {
            viewModel.deleteAllRecords()
            viewModel.deleteAllRecordsCurrency()
        }
    }
}