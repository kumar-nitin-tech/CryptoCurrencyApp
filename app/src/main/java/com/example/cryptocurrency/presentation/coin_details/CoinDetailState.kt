package com.example.cryptocurrency.presentation.coin_details

import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetails

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins:CoinDetails? = null,
    val error: String = ""
)