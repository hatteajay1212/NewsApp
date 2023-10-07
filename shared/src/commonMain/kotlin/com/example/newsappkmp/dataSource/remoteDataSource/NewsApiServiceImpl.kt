package com.example.newsappkmp.dataSource.remoteDataSource

import com.example.newsappkmp.Utils.ApiEndPoints
import com.example.newsappkmp.Utils.ApiParameters
import com.example.newsappkmp.Utils.Constants
import com.example.newsappkmp.Utils.HttpErrorCodes
import com.example.newsappkmp.Utils.LanguageCodes
import com.example.newsappkmp.Utils.NetworkConfig
import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesApiResponse
import com.example.newsappkmp.resultHandlers.ApiResponse
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class NewsApiServiceImpl(

) : NewsApiService{

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getTopHeadlines(): ApiResponse<ModelTopHeadlinesApiResponse>{
        val urlString : String = NetworkConfig.BASE_URL + ApiEndPoints.TOP_HEADLINES

        val response = NewsApiClient.getApiClient().get {
            url(urlString)
            parameter(ApiParameters.PARAMETER_LANGUAGE,LanguageCodes.LANG_CODE_ENGLISH)
            parameter(ApiParameters.PARAMETER_API_KEY,Constants.API_KEY)
        }

        return if (HttpErrorCodes.SUCCESS == response.status.value){
            val topHeadlinesApiResponseBody = response.bodyAsText()
            try {
                val modelTopHeadlinesResponse = json.decodeFromString<ModelTopHeadlinesApiResponse>(topHeadlinesApiResponseBody)
                ApiResponse.Success(data = modelTopHeadlinesResponse)
            }catch (e : Exception){
                ApiResponse.Failure(errorCode = 0, errorMessage = e.message.toString())
            }
        }else{
            ApiResponse.Failure(errorCode = response.status.value, errorMessage = response.status.description)
        }
    }
}