package com.alberto.powerful.calculator.data.dto


data class RecordRequest (
    val operation: String,
    val result: String
)

data class RecordCurrencyRequest (
    val from: String,
    val to: String,
)