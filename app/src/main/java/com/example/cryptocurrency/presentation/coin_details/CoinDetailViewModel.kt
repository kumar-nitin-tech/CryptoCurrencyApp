package com.example.cryptocurrency.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.use_cases.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//to avoid view model factory provider
@HiltViewModel
class CoinDetailViewModel  @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    //State of the app
    //
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoins(coinId = it)
        }
    }

    private fun getCoins(coinId :String){
        getCoinUseCase(coinId).onEach {result->
            when(result){
                is Resource.Success ->{
                    _state.value = CoinDetailState(coins = result.data)
                }
                is Resource.Error-> {
                    _state.value =
                        CoinDetailState(error = result.message ?: "An Unexpected error occured")
                }
                    is Resource.Loading->{
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
}
