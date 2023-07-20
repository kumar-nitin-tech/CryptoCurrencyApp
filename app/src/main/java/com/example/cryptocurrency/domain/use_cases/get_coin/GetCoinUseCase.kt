package com.example.cryptocurrency.domain.use_cases.get_coin

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetails
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//Now this Reusable and Testable
//This only get the coin details by id
class GetCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    //Override the invoke operator so that we use getcoinusecase class as function
    //We use flow because we can have multiple value and resource to emit the success, error, or loading for the list of coins
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {
            //When we invoke the function means we want the coins data first
            //First we want to load the screen
            emit(Resource.Loading<CoinDetails>())
            //This will receive in our view model and we know that we have to show loading in our ui
            //we get the list of coins here
            val coin = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetails>(coin))
        }
        //if http request fail
        catch (e:HttpException){
            emit(Resource.Error<CoinDetails>(e.localizedMessage ?: "An Unexpected error"))
        }
        //if api request doesn't happen due to internet or server is not available
        catch ( e:IOException){
            emit(Resource.Error<CoinDetails>("Couldn't reach to the server. Check your Internet Connection"))
        }
    }
}