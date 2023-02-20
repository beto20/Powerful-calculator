package com.alberto.powerful.calculator.ui.history

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.powerful.calculator.data.dto.RecordCurrencyResponse
import com.alberto.powerful.calculator.data.dto.RecordResponse
import com.alberto.powerful.calculator.usecase.GetAllRecord
import com.alberto.powerful.calculator.usecase.GetAllRecordCurrency
import com.alberto.powerful.calculator.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private var getAllRecord: GetAllRecord,
    private var getAllRecordCurrency: GetAllRecordCurrency,
    private val sharedPreferences: SharedPreferences
) :ViewModel() {

    private val _state = MutableStateFlow<RecordState>(RecordState.Init)
    val state: StateFlow<RecordState> get() = _state

    fun getSwitchModeValue(): String {
        return sharedPreferences.getString(Constants.SWITCH_PREFERENCE, "") ?: ""
    }

    fun getAllRecord() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getAllRecord.invoke()
                }
                response.collect {
                    _state.value = RecordState.SuccessRecord(it)
                }
            } catch (e: Exception) {
                _state.value = RecordState.Error(e.message.toString())
            }
        }
    }

    fun getAllRecordCurrency() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getAllRecordCurrency.invoke()
                }
                response.collect {
                    _state.value = RecordState.SuccessRecordCurrency(it)
                }
            } catch (e: Exception) {
                _state.value = RecordState.Error(e.message.toString())
            }
        }
    }

    sealed class RecordState {
        object Init: RecordState()
        data class Error(val message: String): RecordState()
        data class SuccessRecord(val recordResponse: List<RecordResponse>): RecordState()
        data class SuccessRecordCurrency(val recordCurrencyResponse: List<RecordCurrencyResponse>): RecordState()
    }
}