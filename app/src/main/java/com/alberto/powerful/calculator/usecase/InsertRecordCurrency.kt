package com.alberto.powerful.calculator.usecase

import com.alberto.powerful.calculator.data.dto.RecordCurrencyRequest
import com.alberto.powerful.calculator.data.repository.RecordCurrencyRepository
import javax.inject.Inject

class InsertRecordCurrency @Inject constructor(private val recordCurrencyRepository: RecordCurrencyRepository) {

    suspend operator fun invoke(recordCurrencyRequest: RecordCurrencyRequest) {
        recordCurrencyRepository.insert(recordCurrencyRequest)
    }
}