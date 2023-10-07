package com.example.newsappkmp.viewModels

import com.example.newsappkmp.dataSource.remoteDataSource.NewsApiService
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

    private val _mutableTrendingPublicationsListFlow = MutableStateFlow(arrayListOf<String>())
    private val _mutableNewsListFlow = MutableStateFlow(arrayListOf<String>())

    val state = combine(_newsHomeViewModelState,_mutableNewsListFlow,_mutableTrendingPublicationsListFlow){newsViewModelState,newsList,trendingPublicationsList ->
        newsViewModelState.copy(trendingPublicationsList = trendingPublicationsList,trendingNewsList = newsList)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NewsAppViewModelState())

    fun fetchTopHeadlines(){
        viewModelScope.launch {
            var result = service.getTopHeadlines()
        }
    }
}

data class NewsAppViewModelState(
    val trendingPublicationsList : ArrayList<String> = arrayListOf(),
    val trendingNewsList : ArrayList<String> = arrayListOf()
)