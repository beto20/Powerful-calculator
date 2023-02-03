package com.alberto.powerful.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dbRecord: DbRecord)

    @Query("SELECT *FROM tb_record")
    fun getAll(): Flow<List<DbRecord>>
}