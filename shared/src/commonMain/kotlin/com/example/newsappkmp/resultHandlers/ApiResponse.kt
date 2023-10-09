package com.example.newsappkmp.resultHandlers

sealed class ApiResponse<T>(
    val data : T? = null,
    val errorMessage : String? = null,
    val errorCode : String? = null
){
    class Success<T> (data : T) : ApiResponse<T>(data = data)
    class Failure<T> (errorCode : String, errorMessage : String) : ApiResponse<T>(errorCode = errorCode,errorMessage = errorMessage)
}
