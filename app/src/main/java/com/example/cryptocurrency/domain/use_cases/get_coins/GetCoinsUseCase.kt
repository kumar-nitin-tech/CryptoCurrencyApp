package com.example.cryptocurrency.domain.use_cases.get_coins

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetails
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//Now this Reusable and Testable
//This only get the coins
class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    //Override the invoke operator so that we use getcoinusecase class as function
    //We use flow because we can have multiple value and resource to emit the success, error, or loading for the list of coins
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            //When we invoke the function means we want the coins data first
            //First we want to load the screen
            emit(Resource.Loading<List<Coin>>())
            //This will receive in our view model and we know that we have to show loading in our ui
            //we get the list of coins here
            val coins = coinRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        }
        //if http request fail
        catch (e:HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An Unexpected error"))
        }
        //if api request doesn't happen due to internet or server is not available
        catch ( e:IOException){
            emit(Resource.Error<List<Coin>>("Couldn't reach to the server. Check your Internet Connection"))
        }
    }
}