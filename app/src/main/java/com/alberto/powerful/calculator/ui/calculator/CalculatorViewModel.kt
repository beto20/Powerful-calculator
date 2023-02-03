package com.alberto.powerful.calculator.ui.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.powerful.calculator.data.dto.RecordRequest
import com.alberto.powerful.calculator.usecase.InsertRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val insertRecord: InsertRecord
) : ViewModel() {

    fun saveRecord(operation: String, result: String) {
        viewModelScope.launch {
            insertRecord.invoke(RecordRequest(operation, result))
        }
    }
}