package com.alberto.powerful.calculator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbRecord::class, DbRecordCurrency::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun recordDao(): RecordDao
    abstract fun recordCurrencyDao(): RecordCurrencyDao

}