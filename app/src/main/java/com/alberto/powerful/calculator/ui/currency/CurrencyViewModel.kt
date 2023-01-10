package com.alberto.powerful.calculator.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.powerful.calculator.domain.Converter
import com.alberto.powerful.calculator.usecase.ConvertCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val convertCurrency: ConvertCurrency,
): ViewModel() {


    private val _state = MutableLiveData<CurrencyState>(CurrencyState.Init)
    val state: LiveData<CurrencyState> = _state

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