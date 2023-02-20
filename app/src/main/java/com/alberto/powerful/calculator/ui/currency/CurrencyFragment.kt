package com.alberto.powerful.calculator.ui.currency

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.databinding.FragmentCurrencyBinding
import com.alberto.powerful.calculator.domain.Converter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment(R.layout.fragment_currency,) {

    private lateinit var binding: FragmentCurrencyBinding
    private val viewModel: CurrencyViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrencyBinding.bind(view)

        validateColorPreference()
        populateCurrencySymbols()
        setUpObserver()

        binding.btnConvert.setOnClickListener {
            convertCurrency()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun validateColorPreference() = with(binding) {
        val mode = viewModel.getSwitchModeValue()
        if (mode == "light") {
            currencyFragment.setBackgroundColor(R.color.lightMode)
        }
    }


    private fun populateCurrencySymbols() = with(binding) {
        val list: MutableList<String> = arrayListOf()
        list.add("PEN")
        list.add("USD")
        list.add("EUR")

        binding.spCurrency.setAdapter(ArrayAdapter(requireContext(), R.layout.item_spinner_symbol, list))
        binding.spCurrencyRequested.setAdapter(ArrayAdapter(requireContext(), R.layout.item_spinner_symbol, list))
    }

    private fun convertCurrency() = with(binding) {
        val amountText = edtIngressAmount.text.toString()
        val to = spCurrency.text.toString()
        val from = spCurrencyRequested.text.toString()

        val amount = amountText.toFloat()

        viewModel.convert(to, from, amount)
    }

    private fun setUpObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                CurrencyViewModel.CurrencyState.Init -> Unit
                is CurrencyViewModel.CurrencyState.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                is CurrencyViewModel.CurrencyState.SuccessConvert -> {
                    persistRecordCurrency(it.converter)
                    binding.edtIngressAmountResponse.setText(it.converter.result.toString())
                }
            }
        }
    }

    private fun persistRecordCurrency(converter: Converter) {
        val toAmount = "${converter.query.to} : ${converter.result}"
        val fromAmount = "${converter.query.from} : ${converter.query.amount}"
        viewModel.saveRecord(toAmount, fromAmount)
    }
}