package com.example.cryptocurrency.di

import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.data.remote.CoinApi
import com.example.cryptocurrency.data.respository.CoinRepositoryImpl
import com.example.cryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//DI is used to avoid harcoded instance for every require instance
//Module is
@Module
//Singleton means as long app live this survives
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi{
        //here we provide dagger how to create retrofit instance and it would we single instance so use singleton annotation
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepo(api: CoinApi): CoinRepository{
        return CoinRepositoryImpl(api)
    }
}