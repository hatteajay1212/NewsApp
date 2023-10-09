package com.example.newsappkmp.dataTransferObjects

import io.ktor.http.Url
import kotlinx.serialization.Serializable

@Serializable
data class ModelTopHeadlinesArticle(
    val source : ModelTopHeadlinesSource,
    val author : String?,
    val title : String?,
    val description : String?,
    val url : String?,
    val urlToImage : String?,
    val publishedAt : String?,
    val content : String?
)
