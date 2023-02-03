package com.alberto.powerful.calculator.data.datasource.impl

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.alberto.powerful.calculator.data.client.CurrencyConverterClient
import com.alberto.powerful.calculator.data.dto.AmountConvertResponse
import com.alberto.powerful.calculator.data.datasource.CurrencyRemoteDataSource
import com.alberto.powerful.calculator.domain.*
import com.alberto.powerful.calculator.util.Constants
import com.alberto.powerful.calculator.util.ErrorCatalog
import javax.inject.Inject

class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val currencyConverterClient: CurrencyConverterClient
): CurrencyRemoteDataSource {

    // TODO:: Refactor code, in order to avoid duplicate the same try-catch en many request
    override suspend fun getAmountConverted(to: String, from: String, amount: Float): Either<ErrorCatalog, Converter> {
        return try {
            currencyConverterClient.convertAmount(
                Constants.API_KEY,
                to,
                from,
                amount
            ).let { symbolsResponse ->
                symbolsResponse.toDomain()
            }.right()
        } catch (exception: Exception) {
            exception.cause.let {
                ErrorCatalog.Timeout
            }.left()
        }
    }


    private fun AmountConvertResponse.toDomain(): Converter {
        val converterInfo = Info(info.rate, info.timestamp)
        val converterQuery = Query(query.amount, query.from, query.to)

        return Converter(date, converterInfo , converterQuery, result, success)
    }

}