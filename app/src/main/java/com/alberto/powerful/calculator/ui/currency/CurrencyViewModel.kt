package com.alberto.powerful.calculator.ui.currency

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.powerful.calculator.data.dto.RecordCurrencyRequest
import com.alberto.powerful.calculator.domain.Converter
import com.alberto.powerful.calculator.usecase.ConvertCurrency
import com.alberto.powerful.calculator.usecase.InsertRecordCurrency
import com.alberto.powerful.calculator.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val convertCurrency: ConvertCurrency,
    private val insertRecordCurrency: InsertRecordCurrency,
    private val sharedPreferences: SharedPreferences
): ViewModel() {


    private val _state = MutableLiveData<CurrencyState>(CurrencyState.Init)
    val state: LiveData<CurrencyState> = _state

    fun getSwitchModeValue(): String {
        return sharedPreferences.getString(Constants.SWITCH_PREFERENCE, "") ?: ""
    }

    fun saveRecord(to: String, from: String) {
        viewModelScope.launch {
            insertRecordCurrency.invoke(RecordCurrencyRequest(to, from))
        }
    }

    fun convert(to: String, from: String, amount: Float) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    convertCurrency(to, from, amount)
                }

                response.fold(
                    {
                        _state.value = CurrencyState.Error(it.toString())
                    },
                    {
                        _state.value = CurrencyState.SuccessConvert(it)
                    }
                )

            } catch (exception: Exception) {
                print(exception.message)
                _state.value = CurrencyState.Error(exception.message.toString())
            }
        }
    }

    sealed class CurrencyState {
        object Init: CurrencyState()

        data class SuccessConvert(val converter: Converter): CurrencyState()
        data class Error(val message: String): CurrencyState()
    }

}