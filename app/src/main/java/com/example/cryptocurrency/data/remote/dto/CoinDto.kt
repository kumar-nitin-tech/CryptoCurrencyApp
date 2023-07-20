package com.example.cryptocurrency.data.remote.dto

import com.example.cryptocurrency.domain.model.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

//Map the coin Dto to coin data class
//This is used to convert coin DTO to coin data class
fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}