package com.alberto.powerful.calculator.util

sealed class ErrorCatalog {

    data class CustomException(val message: String) : ErrorCatalog()

    object Timeout: ErrorCatalog()
    object Unknown: ErrorCatalog()

}