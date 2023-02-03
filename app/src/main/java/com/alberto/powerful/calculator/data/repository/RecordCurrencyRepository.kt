package com.alberto.powerful.calculator.data.repository

import com.alberto.powerful.calculator.data.datasource.RecordCurrencyLocalDataSource
import com.alberto.powerful.calculator.data.dto.RecordCurrencyRequest
import com.alberto.powerful.calculator.data.dto.RecordCurrencyResponse
import com.alberto.powerful.calculator.domain.RecordCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordCurrencyRepository @Inject constructor(private val recordCurrencyLocalDataSource: RecordCurrencyLocalDataSource) {

    suspend fun insert(recordCurrencyRequest: RecordCurrencyRequest) {
        recordCurrencyLocalDataSource.insert(recordCurrencyRequest.toDomain())
    }

    fun getAll(): Flow<List<RecordCurrencyResponse>> {
        return recordCurrencyLocalDataSource.getAll().map {
            it.toResponseList()
        }
    }

    /* MAPPERS */
    private fun List<RecordCurrency>.toResponseList(): List<RecordCurrencyResponse> = map {
        it.toResponse()
    }
    private fun RecordCurrency.toResponse(): RecordCurrencyResponse = RecordCurrencyResponse(from, to)
    private fun RecordCurrencyRequest.toDomain(): RecordCurrency = RecordCurrency(from, to)
}