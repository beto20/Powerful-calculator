package com.alberto.powerful.calculator.data.datasource

import com.alberto.powerful.calculator.domain.RecordCurrency
import kotlinx.coroutines.flow.Flow

interface RecordCurrencyLocalDataSource {

    suspend fun insert(recordCurrency: RecordCurrency)
    fun getAll(): Flow<List<RecordCurrency>>
}