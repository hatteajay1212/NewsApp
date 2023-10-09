package com.example.newsappkmp.android.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsappkmp.viewModels.NewsHomeViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun NewsHomeComposable(navController : NavHostController,viewModel : NewsHomeViewModel){

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            items(
                count = state.trendingNewsList.size
            ){index ->

                val modelTrendingNews = state.trendingNewsList.get(index)

                Card(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .fillParentMaxHeight()
                        .padding(16.dp),
                    shape = RoundedCornerShape(
                        corner = CornerSize(16.dp)
                    )
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        modelTrendingNews.urlToImage?.let {
                            KamelImage(
                                resource = asyncPainterResource(data = it), contentDescription = null,
                                onFailure = {exception->
                                    println("image loading exception -> ${exception.message}")
                                },
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                alpha = 0.5f,
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            IconButton(onClick = {

                            }) {
                                Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
                            }

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            )

                            modelTrendingNews.title?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                modelTrendingNews.source.name?.let {
                                    Text(
                                        text = it,
                                        textAlign = TextAlign.Start,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }

                                modelTrendingNews.publishedAt?.let {
                                    Text(
                                        text = it,
                                        textAlign = TextAlign.End,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            modelTrendingNews.author?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }

        Text(
            text = "Trending Collections",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.labelLarge,
            fontSize = 18.sp
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight().padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        ){
            items(count = state.trendingPublicationsList.size){index ->

                val modelTopHeadlinesSource = state.trendingPublicationsList.get(index)
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(140.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(
                        corner = CornerSize(size = 8.dp)
                    ),
                ) {
                    modelTopHeadlinesSource.name?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(8.dp),
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}