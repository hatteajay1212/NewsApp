package com.example.newsappkmp.resultHandlers

sealed class ApiResponse<T>(
    val data : T? = null,
    val errorMessage : String? = null,
    val errorCode : Int = 0
){
    class Success<T> (data : T) : ApiResponse<T>(data = data)
    class Failure<T> (errorCode : Int, errorMessage : String) : ApiResponse<T>(errorCode = errorCode,errorMessage = errorMessage)
}
