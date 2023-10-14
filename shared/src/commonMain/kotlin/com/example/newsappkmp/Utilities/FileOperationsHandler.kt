package com.example.newsappkmp.Utilities

expect class FileOperationsHandler {

    suspend fun log(tag : String, data : String)

    suspend fun writeToFile(filePath : String, data : String)

    suspend fun readFromFile(filePath: String) : String

    suspend fun deleteFile(filePath : String)
}