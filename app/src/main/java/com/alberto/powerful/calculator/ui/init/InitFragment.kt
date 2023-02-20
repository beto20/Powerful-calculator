package com.alberto.powerful.calculator.ui.init


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.databinding.FragmentInitBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InitFragment: Fragment(R.layout.fragment_init) {

    private lateinit var binding: FragmentInitBinding
    private val viewModel: InitViewModel by viewModels()

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInitBinding.bind(view)

        binding.btnCalculator.setOnClickListener {
            findNavController().navigate(InitFragmentDirections.actionInitFragmentToCalculatorFragment())
        }

        binding.btnExchange.setOnClickListener {
            findNavController().navigate(InitFragmentDirections.actionInitFragmentToCurrencyFragment())
        }

        binding.btnRecord.setOnClickListener {
            findNavController().navigate(InitFragmentDirections.actionInitFragmentToRecordFragment())
        }

        binding.btnConfiguration.setOnClickListener {
            findNavController().navigate(InitFragmentDirections.actionInitFragmentToConfigurationFragment())
        }

        validateColorPreference()
    }

    @SuppressLint("ResourceAsColor")
    private fun validateColorPreference() = with(binding) {
        val mode = viewModel.getSwitchModeValue()
        if (mode == "light") {
            initFragment.setBackgroundColor(R.color.lightMode)
        }
    }
}