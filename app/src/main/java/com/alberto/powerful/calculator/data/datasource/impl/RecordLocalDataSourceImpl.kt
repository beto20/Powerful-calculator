package com.alberto.powerful.calculator.data.datasource.impl

import com.alberto.powerful.calculator.data.datasource.RecordLocalDataSource
import com.alberto.powerful.calculator.data.local.DbRecord
import com.alberto.powerful.calculator.data.local.RecordCurrencyDao
import com.alberto.powerful.calculator.data.local.RecordDao
import com.alberto.powerful.calculator.domain.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordLocalDataSourceImpl @Inject constructor(
    private val recordDao: RecordDao,
) : RecordLocalDataSource {

    override suspend fun insert(record: Record) {
        recordDao.insert(record.toEntity())
    }

    override fun getAll(): Flow<List<Record>> {
        return recordDao.getAll().map {
            it.toDomainList()
        }
    }

    override suspend fun deleteAllRecords() {
        val recordsFlow = recordDao.getAll()
        recordsFlow.collect {
            recordDao.deleteAllRecords(it)
        }
    }

    /* MAPPERS */
    private fun List<DbRecord>.toDomainList(): List<Record> = map{
        it.toDomain()
    }
    private fun DbRecord.toDomain(): Record = Record(operation, result)
    private fun Record.toEntity(): DbRecord = DbRecord(operation, result)
}