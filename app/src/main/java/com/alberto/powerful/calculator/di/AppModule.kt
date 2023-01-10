package com.alberto.powerful.calculator.di

import com.alberto.powerful.calculator.data.client.CurrencyConverterClient
import com.alberto.powerful.calculator.data.remote.CurrencyRemoteDataSource
import com.alberto.powerful.calculator.data.remote.impl.CurrencyRemoteDataSourceImpl
import com.alberto.powerful.calculator.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        HttpLoggingInterceptor.Level.HEADERS
        HttpLoggingInterceptor.Level.BODY
        OkHttpClient
            .Builder()
            .addInterceptor(this)
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(okHttpClient: OkHttpClient): CurrencyConverterClient  {
        return Retrofit
            .Builder()
            .baseUrl(Constants.EXTERNAL_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class AppDataModule {
        @Binds
        abstract fun bindCurrencyRemoteDataSource(currencyRemoteDataSourceImpl: CurrencyRemoteDataSourceImpl): CurrencyRemoteDataSource
    }

}