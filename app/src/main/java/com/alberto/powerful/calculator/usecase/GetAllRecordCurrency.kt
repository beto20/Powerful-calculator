package com.alberto.powerful.calculator.usecase

import com.alberto.powerful.calculator.data.dto.RecordCurrencyResponse
import com.alberto.powerful.calculator.data.repository.RecordCurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRecordCurrency @Inject constructor(private val recordCurrencyRepository: RecordCurrencyRepository) {

    suspend operator fun invoke(): Flow<List<RecordCurrencyResponse>> {
        return recordCurrencyRepository.getAll()
    }
}