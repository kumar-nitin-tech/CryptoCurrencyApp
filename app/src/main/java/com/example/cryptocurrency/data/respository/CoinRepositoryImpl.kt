package com.example.cryptocurrency.data.respository

import com.example.cryptocurrency.data.remote.CoinApi
import com.example.cryptocurrency.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

//Injecting in the DI as Constructor
//We are here implementing the coin repo and updating the interface from coin repo
//We have injected the coin repo so that we can use it anywhere
class CoinRepositoryImpl @Inject constructor(private val api: CoinApi) :CoinRepository{
    //first we get the api instance as a parameter and update di accordingly

    //We updated the coin repo fun with api data
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }

}