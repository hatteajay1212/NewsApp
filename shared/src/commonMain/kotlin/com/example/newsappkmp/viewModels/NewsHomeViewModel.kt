package com.example.newsappkmp.viewModels

import com.example.newsappkmp.dataSource.remoteDataSource.NewsApiService
import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesArticle
import com.example.newsappkmp.dataTransferObjects.ModelTopHeadlinesSource
import com.example.newsappkmp.resultHandlers.ApiResponse
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsHomeViewModel(
    private val service: NewsApiService
) : ViewModel() {
    private val _newsHomeViewModelState = MutableStateFlow(NewsAppViewModelState())

    private val _mutableTrendingPublicationsListFlow = MutableStateFlow(arrayListOf<ModelTopHeadlinesSource>())
    private val _mutableNewsListFlow = MutableStateFlow(arrayListOf<ModelTopHeadlinesArticle>())

    val state = combine(_newsHomeViewModelState,_mutableNewsListFlow,_mutableTrendingPublicationsListFlow){newsViewModelState,newsList,trendingPublicationsList ->
        newsViewModelState.copy(trendingPublicationsList = trendingPublicationsList,trendingNewsList = newsList)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NewsAppViewModelState())

    fun fetchTopHeadlines(){
        viewModelScope.launch {
            val result = service.getTopHeadlines()

            when(result){
                is ApiResponse.Success -> {
                    _mutableNewsListFlow.value = result.data?.articles ?: arrayListOf()
                }

                is ApiResponse.Failure -> {

                }
            }
        }
    }

    fun fetchTopHeadlinesSources(){
        viewModelScope.launch {
            val result = service.getTopHeadlinesSources()

            when (result){

                is ApiResponse.Success -> {
                    _mutableTrendingPublicationsListFlow.value = result.data?.sources ?: arrayListOf()
                }

                is ApiResponse.Failure -> {

                }
            }
        }
    }
}

data class NewsAppViewModelState(
    val trendingPublicationsList : ArrayList<ModelTopHeadlinesSource> = arrayListOf(),
    val trendingNewsList : ArrayList<ModelTopHeadlinesArticle> = arrayListOf()
)