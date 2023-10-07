package com.example.newsappkmp.dataSource.remoteDataSource

import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesApiResponse
import com.example.newsappkmp.resultHandlers.ApiResponse

interface NewsApiService {
    suspend fun getTopHeadlines() : ApiResponse<ModelTopHeadlinesApiResponse>
}