package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrency.data.remote.dto.CoinDto

//It is helpful for testcase
//This not the actual repo which get the data from the api but it is a fake version of repo which behave as a api for fast running of test case
interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String) : CoinDetailsDto
}