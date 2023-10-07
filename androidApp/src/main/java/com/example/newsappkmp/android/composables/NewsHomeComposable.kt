package com.example.newsappkmp.android.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newsappkmp.viewModels.NewsHomeViewModel

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
                .padding(8.dp)
        ){
            items(
                count = state.trendingNewsList.size
            ){index ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, top = 8.dp, end = 24.dp, bottom = 8.dp),
                    shape = RoundedCornerShape(
                        corner = CornerSize(16.dp)
                    )
                ) {
                    Image(
                        painter = painterResource(id = 0), contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )

                    IconButton(onClick = {

                    }) {
                        Icon(painter = painterResource(id = 0), contentDescription = null)
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Text(
                        text = state.trendingNewsList.get(index),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )

                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "USA Today",
                            modifier = Modifier.wrapContentHeight(),
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = "13/10/2022",
                            modifier = Modifier.wrapContentHeight(),
                            textAlign = TextAlign.End
                        )
                    }

                    Text(
                        text = "Mary Walton",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.fillMaxWidth()
                .height(16.dp)
        )

        Text(text = "Trending Publications")

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ){
            items(count = state.trendingPublicationsList.size){index ->
                Card(
                    modifier = Modifier
                        .width(148.dp)
                        .height(96.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(
                        corner = CornerSize(size = 8.dp)
                    )
                ) {
                    Text(
                        text = state.trendingPublicationsList.get(index),
                        modifier = Modifier
                                .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}