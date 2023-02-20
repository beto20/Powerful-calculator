package com.alberto.powerful.calculator.ui.configuration

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.powerful.calculator.usecase.DeleteAllRecords
import com.alberto.powerful.calculator.usecase.DeleteAllRecordsCurrency
import com.alberto.powerful.calculator.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigurationViewModel @Inject constructor(
    private val deleteAllRecords: DeleteAllRecords,
    private val deleteAllRecordsCurrency: DeleteAllRecordsCurrency,
    private val sharedPreferences: SharedPreferences
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

    fun saveSwitchModeValue(mode: String) {
        sharedPreferences.edit().putString(Constants.SWITCH_PREFERENCE, mode).apply()
    }

    fun getSwitchModeValue(): String {
        return sharedPreferences.getString(Constants.SWITCH_PREFERENCE, "") ?: ""
    }
}