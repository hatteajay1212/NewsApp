package com.example.newsappkmp.dataTransferObjects

import kotlinx.serialization.Serializable

@Serializable
data class ModelTopHeadlinesApiResponse(
    val status : String = "",
    val totalResults : Int = 0,
    val articles : ArrayList<ModelTopHeadlinesArticle> = arrayListOf(),
    val code : String? = "",
    val message : String ? = ""
)
