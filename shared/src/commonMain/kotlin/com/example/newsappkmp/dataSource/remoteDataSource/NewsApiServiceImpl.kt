package com.example.newsappkmp.dataSource.remoteDataSource

import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesApiResponse
import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesSourcesResponse
import com.example.newsappkmp.resultHandlers.ApiResponse
import com.example.newsappkmp.Utilities.ConstantUtils
import com.example.newsappkmp.Utilities.FileOperationsHandler
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class NewsApiServiceImpl(
    private val fileHandler: FileOperationsHandler
) : NewsApiService{

    private val json = Json { ignoreUnknownKeys = true }
    private val TAG = "NewsApiServiceImpl"

    override suspend fun getTopHeadlines(): ApiResponse<ModelTopHeadlinesApiResponse>{
        val urlString : String = ConstantUtils.NetworkConfig.BASE_URL + ConstantUtils.ApiEndPoints.TOP_HEADLINES

        val response = NewsApiClient.getApiClient().get {
            url(urlString)
            parameter(ConstantUtils.ApiParameters.PARAMETER_LANGUAGE, ConstantUtils.LanguageCodes.LANG_CODE_ENGLISH)
            parameter(ConstantUtils.ApiParameters.PARAMETER_API_KEY, ConstantUtils.Constants.API_KEY)
        }

        return if(null != response){
            try {
                val topHeadlinesApiResponseBody = response.bodyAsText()
                val modelTopHeadlinesResponse = json.decodeFromString<ModelTopHeadlinesApiResponse>(topHeadlinesApiResponseBody)

                if (ConstantUtils.HttpErrorCodes.SUCCESS == response.status.value){
                    fileHandler.log(TAG,modelTopHeadlinesResponse.toString())
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

        val urlString = ConstantUtils.NetworkConfig.BASE_URL + ConstantUtils.ApiEndPoints.TOP_HEADLINES_SOURCES

        val response = NewsApiClient.getApiClient().get{
            url(urlString)
            parameter(ConstantUtils.ApiParameters.PARAMETER_LANGUAGE, ConstantUtils.LanguageCodes.LANG_CODE_ENGLISH)
            parameter(ConstantUtils.ApiParameters.PARAMETER_API_KEY, ConstantUtils.Constants.API_KEY)
        }

        return if (null != response){
            try {
                val topHeadlinesSourcesResponseBody = response.bodyAsText()
                val modelTopHeadlinesSourcesResponse = json.decodeFromString<ModelTopHeadlinesSourcesResponse>(topHeadlinesSourcesResponseBody)

                if (ConstantUtils.HttpErrorCodes.SUCCESS == response.status.value){
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