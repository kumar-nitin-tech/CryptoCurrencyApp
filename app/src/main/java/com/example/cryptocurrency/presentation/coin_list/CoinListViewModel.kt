package com.example.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.use_cases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//to avoid view model factory provider
@HiltViewModel
class CoinListViewModel  @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    //State of the app
    //
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach {result->
            when(result){
                is Resource.Success ->{
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error->{
                    _state.value = CoinListState(error = result.message ?: "An Unexpected error occured")
                }
                is Resource.Loading->{
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}