package com.alberto.powerful.calculator.usecase

import com.alberto.powerful.calculator.data.dto.RecordRequest
import com.alberto.powerful.calculator.data.repository.RecordRepository
import javax.inject.Inject

class InsertRecord @Inject constructor(private val recordRepository: RecordRepository) {

    suspend operator fun invoke(recordRequest: RecordRequest) {
        recordRepository.insert(recordRequest)
    }
}