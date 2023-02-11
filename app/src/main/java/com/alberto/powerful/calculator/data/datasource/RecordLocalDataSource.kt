package com.alberto.powerful.calculator.data.datasource

import com.alberto.powerful.calculator.domain.Record
import kotlinx.coroutines.flow.Flow

interface RecordLocalDataSource {

    suspend fun insert(record: Record)
    fun getAll(): Flow<List<Record>>
    suspend fun deleteAllRecords()
}