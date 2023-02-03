package com.alberto.powerful.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dbRecordCurrency: DbRecordCurrency)

    @Query("SELECT *FROM tb_record_currency")
    fun getAll(): Flow<List<DbRecordCurrency>>
}