package com.alberto.powerful.calculator.usecase

import com.alberto.powerful.calculator.data.repository.RecordRepository
import javax.inject.Inject

class DeleteAllRecords @Inject constructor(
    private val recordRepository: RecordRepository,
) {

    suspend operator fun invoke() {
        recordRepository.deleteAllRecords()
    }
}