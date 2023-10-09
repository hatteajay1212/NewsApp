package com.example.newsappkmp.dataSource.remoteDataSource

import com.example.newsappkmp.Utils.ApiEndPoints
import com.example.newsappkmp.Utils.ApiParameters
import com.example.newsappkmp.Utils.Constants
import com.example.newsappkmp.Utils.HttpErrorCodes
import com.example.newsappkmp.Utils.HttpStatusDescription
import com.example.newsappkmp.Utils.LanguageCodes
import com.example.newsappkmp.Utils.NetworkConfig
import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesApiResponse
import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesSourcesResponse
import com.example.newsappkmp.resultHandlers.ApiResponse
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
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

        return if(null != response){
            try {
                val topHeadlinesApiResponseBody = response.bodyAsText()
                val modelTopHeadlinesResponse = json.decodeFromString<ModelTopHeadlinesApiResponse>(topHeadlinesApiResponseBody)

                if (HttpErrorCodes.SUCCESS == response.status.value){
                    ApiResponse.Success(modelTopHeadlinesResponse)
                }else{
                    if (null != modelTopHeadlinesResponse.code){
                        ApiResponse.Failure(modelTopHeadlinesResponse.code,modelTopHeadlinesResponse.message ?: "")
                    }else{
                        ApiResponse.Failure(errorCode = "${response.status.value}", errorMessage = response.status.description)
                    }
                }
            }catch (e : Exception){
                ApiResponse.Failure("error",e.message.toString())
            }
        }else{
            ApiResponse.Failure("error","Response From Server is null")
        }
    }

    override suspend fun getTopHeadlinesSources(): ApiResponse<ModelTopHeadlinesSourcesResponse> {

        val urlString = NetworkConfig.BASE_URL + ApiEndPoints.TOP_HEADLINES_SOURCES

        val response = NewsApiClient.getApiClient().get{
            url(urlString)
            parameter(ApiParameters.PARAMETER_LANGUAGE, LanguageCodes.LANG_CODE_ENGLISH)
            parameter(ApiParameters.PARAMETER_API_KEY, Constants.API_KEY)
        }

        return if (null != response){
            try {
                val topHeadlinesSourcesResponseBody = response.bodyAsText()
                val modelTopHeadlinesSourcesResponse = json.decodeFromString<ModelTopHeadlinesSourcesResponse>(topHeadlinesSourcesResponseBody)

                if (HttpErrorCodes.SUCCESS == response.status.value){
                    ApiResponse.Success(modelTopHeadlinesSourcesResponse)
                }else{
                    if (null != modelTopHeadlinesSourcesResponse.code){
                        ApiResponse.Failure(modelTopHeadlinesSourcesResponse.code,modelTopHeadlinesSourcesResponse.message ?: "")
                    }else{
                        ApiResponse.Failure(errorCode = "${response.status.value}", errorMessage = response.status.description)
                    }
                }
            }catch (e : Exception){
                ApiResponse.Failure("error",e.message.toString())
            }
        }else{
            ApiResponse.Failure("error", "Response from server is null")
        }
    }
}