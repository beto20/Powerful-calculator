package com.alberto.powerful.calculator.di

import android.content.Context
import androidx.room.Room
import com.alberto.powerful.calculator.data.client.CurrencyConverterClient
import com.alberto.powerful.calculator.data.local.AppDatabase
import com.alberto.powerful.calculator.data.datasource.CurrencyRemoteDataSource
import com.alberto.powerful.calculator.data.datasource.RecordCurrencyLocalDataSource
import com.alberto.powerful.calculator.data.datasource.RecordLocalDataSource
import com.alberto.powerful.calculator.data.datasource.impl.CurrencyRemoteDataSourceImpl
import com.alberto.powerful.calculator.data.datasource.impl.RecordCurrencyLocalDataSourceImpl
import com.alberto.powerful.calculator.data.datasource.impl.RecordLocalDataSourceImpl
import com.alberto.powerful.calculator.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "dbCalculator"
    ).build()

    @Provides
    @Singleton
    fun provideRecordDao(db: AppDatabase) = db.recordDao()

    @Provides
    @Singleton
    fun provideRecordCurrencyDao(db: AppDatabase) = db.recordCurrencyDao()

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class AppDataModule {
        @Binds
        abstract fun bindCurrencyRemoteDataSource(currencyRemoteDataSourceImpl: CurrencyRemoteDataSourceImpl): CurrencyRemoteDataSource
        @Binds
        abstract fun bindRecordLocalDataSource(recordLocalDataSourceImpl: RecordLocalDataSourceImpl): RecordLocalDataSource
        @Binds
        abstract fun bindRecordCurrencyLocalDataSource(recordCurrencyLocalDataSourceImpl: RecordCurrencyLocalDataSourceImpl): RecordCurrencyLocalDataSource

    }

}