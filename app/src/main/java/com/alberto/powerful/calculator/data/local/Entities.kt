package com.alberto.powerful.calculator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_record")
data class DbRecord(

    val operation: String,
    val result: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(tableName = "tb_record_currency")
data class DbRecordCurrency(
    val from: String,
    val to: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}