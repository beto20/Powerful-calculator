package com.alberto.powerful.calculator.ui.init


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.databinding.FragmentInitBinding

class InitFragment: Fragment(R.layout.fragment_init) {

    private lateinit var binding: FragmentInitBinding

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

    }
}