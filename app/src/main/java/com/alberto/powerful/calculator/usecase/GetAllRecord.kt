package com.alberto.powerful.calculator.usecase

import com.alberto.powerful.calculator.data.dto.RecordResponse
import com.alberto.powerful.calculator.data.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRecord @Inject constructor(private val recordRepository: RecordRepository) {

    suspend operator fun invoke(): Flow<List<RecordResponse>> {
        return recordRepository.getAll()
    }
}