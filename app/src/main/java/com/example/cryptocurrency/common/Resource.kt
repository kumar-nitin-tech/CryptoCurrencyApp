package com.example.cryptocurrency.common

//This is generic rapid class
//We can put this class for any type of object
//This is for network call we data and give a message
sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T) :  Resource<T>(data)
    class Error<T>(message: String, data: T? = null) :  Resource<T>(data,message)
    class Loading<T>(data: T? = null) :  Resource<T>(data)
}
