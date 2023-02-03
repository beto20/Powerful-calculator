package com.alberto.powerful.calculator.data.datasource.impl

import com.alberto.powerful.calculator.data.datasource.RecordCurrencyLocalDataSource
import com.alberto.powerful.calculator.data.local.DbRecordCurrency
import com.alberto.powerful.calculator.data.local.RecordCurrencyDao
import com.alberto.powerful.calculator.domain.RecordCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordCurrencyLocalDataSourceImpl @Inject constructor(private val recordCurrencyDao: RecordCurrencyDao) : RecordCurrencyLocalDataSource {

    override suspend fun insert(recordCurrency: RecordCurrency) {
        recordCurrencyDao.insert(recordCurrency.toEntity())
    }

    override fun getAll(): Flow<List<RecordCurrency>> {
        return recordCurrencyDao.getAll().map {
            it.toDomainList()
        }
    }

    /* MAPPERS */
    private fun List<DbRecordCurrency>.toDomainList(): List<RecordCurrency> = map {
        it.toDomain()
    }
    private fun DbRecordCurrency.toDomain(): RecordCurrency = RecordCurrency(from, to)
    private fun RecordCurrency.toEntity(): DbRecordCurrency = DbRecordCurrency(from, to)
}