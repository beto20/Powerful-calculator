package com.alberto.powerful.calculator.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dbRecordCurrency: DbRecordCurrency)

    @Query("SELECT *FROM tb_record_currency")
    fun getAll(): Flow<List<DbRecordCurrency>>

    @Delete
    suspend fun deleteAllRecordsCurrency(records: List<DbRecordCurrency>)
}