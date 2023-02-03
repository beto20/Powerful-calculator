package com.alberto.powerful.calculator.ui.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.databinding.FragmentCalculatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    private lateinit var binding: FragmentCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    private var firstNumber = 0.0F
    private var secondNumber = 0.0F

    private var operation = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalculatorBinding.bind(view)

        events()
        navigationEvents()
    }

    private fun events() {
        numberPressedEvents()
        operationPressedEvents()
        calculateEvent()
        cleanScreenEvent()
    }

    private fun navigationEvents() {

        binding.ivCurrency.setOnClickListener {
            findNavController().navigate(CalculatorFragmentDirections.actionCalculatorFragmentToCurrencyFragment())
        }

        binding.ivHistory.setOnClickListener {
            findNavController().navigate(CalculatorFragmentDirections.actionCalculatorFragmentToRecordFragment())
        }

        binding.ivSettings.setOnClickListener {
            findNavController().navigate(CalculatorFragmentDirections.actionCalculatorFragmentToConfigurationFragment())
        }

    }

    private fun numberPressedEvents() = with(binding) {
        btnPeriod.setOnClickListener { numberPressed(".") }
        btnZero.setOnClickListener { numberPressed("0") }
        btnOne.setOnClickListener { numberPressed("1") }
        btnTwo.setOnClickListener { numberPressed("2") }
        btnThree.setOnClickListener { numberPressed("3") }
        btnFour.setOnClickListener { numberPressed("4") }
        btnFive.setOnClickListener { numberPressed("5") }
        btnSix.setOnClickListener { numberPressed("6") }
        btnSeven.setOnClickListener { numberPressed("7") }
        btnEight.setOnClickListener { numberPressed("8") }
        btnNine.setOnClickListener { numberPressed("9") }
    }

    private fun operationPressedEvents() = with(binding) {
        btnDivide.setOnClickListener { operationPressed("DIVIDE") }
        btnMulti.setOnClickListener { operationPressed("MULTI") }
        btnMinus.setOnClickListener { operationPressed("MINUS") }
        btnPlus.setOnClickListener { operationPressed("PLUS") }
        btnPercentage.setOnClickListener { operationPressed("PERCENTAGE") }
        btnPlusMinus.setOnClickListener { operationPressed("CHANGE_VALUE") }
    }

    private fun calculateEvent() = with(binding) {
        btnEquals.setOnClickListener { calculate() }
    }

    private fun cleanScreenEvent() = with(binding) {
        btnClean.setOnClickListener {
            tvResult.text = ""
            tvOperation.text = ""
            firstNumber = 0.0F
            secondNumber = 0.0F
            operation = ""
        }
    }

    private fun calculate() = with(binding) {
        var completeOperation = ""
        var result = 0.0F

        when(operation) {
            "DIVIDE" -> {
                if (firstNumber != 0.0F) {
                    result = firstNumber / secondNumber
                    completeOperation = "$firstNumber / $secondNumber"
                } else {
                    tvResult.text = firstNumber.toString()
                    return@with
                }
            }
            "MULTI" -> {
                result = firstNumber * secondNumber
                completeOperation = "$firstNumber * $secondNumber"
            }
            "MINUS" -> {
                result = firstNumber - secondNumber
                completeOperation = "$firstNumber - $secondNumber"
            }
            "PLUS" -> {
                result = firstNumber + secondNumber
                completeOperation = "$firstNumber + $secondNumber"
            }
            else -> {
                result = 0.0F
            }
        }

        val finalResult = if("$result".endsWith(".0")) "$result".replace(".0","") else "%.2f".format(result)

        tvResult.text = finalResult
        viewModel.saveRecord(completeOperation, "= $finalResult")
    }

    private fun operationPressed(operationType: String) = with(binding) {
        var symbol = ""
        val valueNumber = tvResult.text.toString()
        if (firstNumber == 0.0F || valueNumber == "")
            return@with

        firstNumber = valueNumber.toFloat()
        operation = operationType

        when(operationType) {
            "DIVIDE" -> symbol = "/"
            "MULTI" -> symbol = "*"
            "MINUS" -> symbol = "-"
            "PLUS" -> symbol = "+"
            "PERCENTAGE" -> {
                tvOperation.text = "%"
                tvResult.text = (firstNumber / 100).toString()
                return@with
            }
            "CHANGE_VALUE" -> {
                var newValue = ""
                tvOperation.text = "-/+"

                if (firstNumber.toString().contains("-")) {
                    newValue = firstNumber.toString().removePrefix("-")

                    tvResult.text = validateResult(newValue)
                } else {
                    newValue = "-${firstNumber}"
                    tvResult.text = validateResult(newValue)
                }
                return@with
            }
        }
        tvOperation.text = symbol
        tvResult.text = ""
    }

    private fun validateResult(result: String): String {
        return if("$result".endsWith(".0")) "$result".replace(".0","")
        else "%.2f".format(result)
    }

    @SuppressLint("SetTextI18n")
    private fun numberPressed(number: String) = with(binding) {
        if (tvResult.text == "0" && number != ".") {
            tvResult.text = "$number"
        } else {
            if (tvResult.text.contains(".", true) && number == ".") tvResult.text = "${tvResult.text}"
            else tvResult.text = "${tvResult.text}$number"
        }

        if (operation.isEmpty()) firstNumber = tvResult.text.toString().toFloat()
        else secondNumber = tvResult.text.toString().toFloat()
    }
}