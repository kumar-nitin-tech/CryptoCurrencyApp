package com.example.cryptocurrency.data.remote

import com.example.cryptocurrency.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {
    //This interface will get the data from api
    @GET("/v1/coins")// We use suspend fun to use coroutine while getting data from the api
    suspend fun getCoins(): List<CoinDto>// There is the list of coins and data is represented in the form the coin Dto

    //This is to get coin details for specific coin
    @GET("/v1/coins/{coin_id}")
    suspend fun getCoinById(@Path("coin_id") coinId: String): CoinDetailsDto
}