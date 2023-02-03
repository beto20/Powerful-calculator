package com.alberto.powerful.calculator.data.datasource

import arrow.core.Either
import com.alberto.powerful.calculator.domain.Converter
import com.alberto.powerful.calculator.util.ErrorCatalog

interface CurrencyRemoteDataSource {

    suspend fun getAmountConverted(to: String, from: String, amount: Float): Either<ErrorCatalog, Converter>
}