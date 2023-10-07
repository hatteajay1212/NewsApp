package com.example.newsappkmp.viewModels

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class NewsHomeViewModel : ViewModel() {
    private val _newsHomeViewModelState = MutableStateFlow(NewsAppViewModelState())

    private val _mutableTrendingPublicationsListFlow = MutableStateFlow(arrayListOf<String>())
    private val _mutableNewsListFlow = MutableStateFlow(arrayListOf<String>())

    val state = combine(_newsHomeViewModelState,_mutableNewsListFlow,_mutableTrendingPublicationsListFlow){newsViewModelState,newsList,trendingPublicationsList ->
        newsViewModelState.copy(trendingPublicationsList = trendingPublicationsList,trendingNewsList = newsList)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NewsAppViewModelState())
}

data class NewsAppViewModelState(
    val trendingPublicationsList : ArrayList<String> = arrayListOf(),
    val trendingNewsList : ArrayList<String> = arrayListOf()
)