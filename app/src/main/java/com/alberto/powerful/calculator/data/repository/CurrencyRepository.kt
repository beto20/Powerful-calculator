package com.alberto.powerful.calculator.data.repository

import arrow.core.Either
import com.alberto.powerful.calculator.data.remote.CurrencyRemoteDataSource
import com.alberto.powerful.calculator.domain.Converter
import com.alberto.powerful.calculator.util.ErrorCatalog
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource
) {


    suspend fun getAmountConverted(to: String, from: String, amount: Float): Either<ErrorCatalog, Converter> {
        return currencyRemoteDataSource.getAmountConverted(to, from, amount)
    }

}