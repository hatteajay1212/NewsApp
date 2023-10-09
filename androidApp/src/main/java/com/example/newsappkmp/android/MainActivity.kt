package com.example.newsappkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.newsappkmp.android.composables.NewsHomeComposable
import com.example.newsappkmp.dataSource.remoteDataSource.NewsApiServiceImpl
import com.example.newsappkmp.viewModels.NewsHomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val newsHomeViewModel : NewsHomeViewModel = NewsHomeViewModel(
                    service = NewsApiServiceImpl()
                )

                newsHomeViewModel.fetchTopHeadlines()
                newsHomeViewModel.fetchTopHeadlinesSources()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsHomeComposable(navController = navController, viewModel = newsHomeViewModel)
                }
            }
        }
    }
}
