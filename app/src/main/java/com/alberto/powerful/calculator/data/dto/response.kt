package com.alberto.powerful.calculator.data.dto

data class AmountConvertResponse(
    val date: String,
    val info: Info,
    val query: Query,
    val result: Double,
    val success: Boolean
)
data class Info(
    val rate: Double,
    val timestamp: Int
)
data class Query(
    val amount: Int,
    val from: String,
    val to: String
)

data class RecordResponse (
    val operation: String,
    val result: String
)

data class RecordCurrencyResponse (
    val from: String,
    val to: String,
)