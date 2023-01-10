package com.alberto.powerful.calculator.data.client

import com.alberto.powerful.calculator.data.dto.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyConverterClient {

    @GET("exchangerates_data/convert")
    suspend fun convertAmount(@Header("apikey") apikey: String,
                              @Query("to") to: String,
                              @Query("from") from: String,
                              @Query("amount") amount: Float): AmountConvertResponse


}
