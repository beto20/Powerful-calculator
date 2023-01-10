package com.alberto.powerful.calculator.usecase

import arrow.core.Either
import com.alberto.powerful.calculator.data.repository.CurrencyRepository
import com.alberto.powerful.calculator.domain.Converter
import com.alberto.powerful.calculator.util.ErrorCatalog
import javax.inject.Inject

class ConvertCurrency @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {

    suspend operator fun invoke(to: String, from: String, amount: Float): Either<ErrorCatalog, Converter> {
        return currencyRepository.getAmountConverted(to, from, amount)
    }
}