package com.example.newsappkmp.dataTransferObjects

import kotlinx.serialization.Serializable

@Serializable
data class ModelTopHeadlinesApiResponse(
    val status : String,
    val totalResults : Int,
    val articles : ArrayList<ModelTopHeadlinesArticle>,
    val code : String? = "",
    val message : String ? = ""
)
