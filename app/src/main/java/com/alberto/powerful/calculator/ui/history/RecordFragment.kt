package com.alberto.powerful.calculator.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.databinding.FragmentRecordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecordFragment : Fragment(R.layout.fragment_record) {

    private lateinit var binding: FragmentRecordBinding
    private val viewModel: RecordViewModel by viewModels()
    private val recordAdapter: RecordAdapter by lazy {
        RecordAdapter(emptyList())
    }
    private val recordCurrencyAdapter: RecordCurrencyAdapter by lazy {
        RecordCurrencyAdapter(emptyList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecordBinding.bind(view)

        init()
        setUpAdapter()
        collectData()
    }

    private fun init() {
        viewModel.getAllRecord()
        viewModel.getAllRecordCurrency()
    }

    private fun setUpAdapter() = with(binding) {
        rvRecord.adapter = recordAdapter
        rvConverterRecord.adapter = recordCurrencyAdapter
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    updateUI(it)
                }
            }
        }
    }

    private fun updateUI(state: RecordViewModel.RecordState) {
        when(state) {
            RecordViewModel.RecordState.Init -> Unit
            is RecordViewModel.RecordState.Error -> println(state.message)
            is RecordViewModel.RecordState.SuccessRecord -> recordAdapter.getAllRecord(state.recordResponse)
            is RecordViewModel.RecordState.SuccessRecordCurrency -> recordCurrencyAdapter.getAllRecordCurrency(state.recordCurrencyResponse)
        }
    }
}