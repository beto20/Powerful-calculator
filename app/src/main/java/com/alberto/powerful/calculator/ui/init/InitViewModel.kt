package com.alberto.powerful.calculator.ui.init

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.alberto.powerful.calculator.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InitViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    fun getSwitchModeValue(): String {
        return sharedPreferences.getString(Constants.SWITCH_PREFERENCE, "") ?: ""
    }
}