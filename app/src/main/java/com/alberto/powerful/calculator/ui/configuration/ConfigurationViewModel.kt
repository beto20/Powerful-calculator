package com.alberto.powerful.calculator.ui.configuration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.powerful.calculator.usecase.DeleteAllRecords
import com.alberto.powerful.calculator.usecase.DeleteAllRecordsCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigurationViewModel @Inject constructor(
    private val deleteAllRecords: DeleteAllRecords,
    private val deleteAllRecordsCurrency: DeleteAllRecordsCurrency
): ViewModel() {

    fun deleteAllRecords() {
        viewModelScope.launch {
            deleteAllRecords.invoke()
        }
    }
    fun deleteAllRecordsCurrency() {
        viewModelScope.launch {
            deleteAllRecordsCurrency.invoke()
        }
    }
}