package com.alberto.powerful.calculator.data.repository

import com.alberto.powerful.calculator.data.datasource.RecordLocalDataSource
import com.alberto.powerful.calculator.data.dto.RecordRequest
import com.alberto.powerful.calculator.data.dto.RecordResponse
import com.alberto.powerful.calculator.domain.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordRepository @Inject constructor(private val recordRemoteDataSource: RecordLocalDataSource) {

    suspend fun insert(recordRequest: RecordRequest) {
        recordRemoteDataSource.insert(recordRequest.toDomain())
    }

     fun getAll(): Flow<List<RecordResponse>> {
        return recordRemoteDataSource.getAll().map {
            it.toResponseList()
        }
    }

    /* MAPPERS */
    private fun List<Record>.toResponseList(): List<RecordResponse> = map {
        it.toResponse()
    }
    private fun Record.toResponse(): RecordResponse = RecordResponse(operation, result)
    private fun RecordRequest.toDomain(): Record = Record(operation, result)
}