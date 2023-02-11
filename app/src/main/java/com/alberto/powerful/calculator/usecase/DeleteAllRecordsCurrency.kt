package com.alberto.powerful.calculator.usecase

import com.alberto.powerful.calculator.data.repository.RecordCurrencyRepository
import javax.inject.Inject

class DeleteAllRecordsCurrency @Inject constructor(
    private val recordCurrencyRepository: RecordCurrencyRepository
) {

    suspend operator fun invoke() {
        recordCurrencyRepository.deleteAllRecordsCurrency()
    }
}